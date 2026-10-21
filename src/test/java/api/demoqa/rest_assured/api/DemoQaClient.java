package api.demoqa.rest_assured.api;


import api.demoqa.rest_assured.models.Credentials;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DemoQaClient {
    private static final int TIMEOUT_MS = 30_000;

    private final RequestSpecification spec;

    public DemoQaClient() {
        this(null);
    }

    public DemoQaClient(String baseUrl) {
        // dotenv.get() читает и .env, и системные переменные окружения
        String url = baseUrl != null ? baseUrl : Dotenv.configure().ignoreIfMissing().load().get("DEMOQA_HOST");
        if (url == null || url.isBlank()) {
            throw new IllegalStateException(
                    "Не задан адрес API: передай baseUrl или установи переменную окружения DEMOQA_HOST");
        }

        RestAssuredConfig config = RestAssuredConfig.config().httpClient(
                HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", TIMEOUT_MS)
                        .setParam("http.socket.timeout", TIMEOUT_MS));

        this.spec = new RequestSpecBuilder()
                .setBaseUri(url.replaceAll("/+$", ""))
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setConfig(config)
                // заменяет ручные allure.attach: request/response попадают в отчёт сами
                .addFilter(new AllureRestAssured())
                .build();
    }

    private RequestSpecification request(String token) {
        RequestSpecification req = RestAssured.given().spec(spec);
        return token == null ? req : req.header("Authorization", "Bearer " + token);
    }

    @Step("Создание пользователя")
    public Response createUser(String userName, String password) {
        return request(null)
                .body(new Credentials(userName, password))
                .post("/Account/v1/User");
    }

    @Step("Генерация токена")
    public Response generateToken(String userName, String password) {
        return request(null)
                .body(new Credentials(userName, password))
                .post("/Account/v1/GenerateToken");
    }

    @Step("Проверка авторизации пользователя")
    public Response authorized(String userName, String password) {
        return request(null)
                .body(new Credentials(userName, password))
                .post("/Account/v1/Authorized");
    }

    @Step("Получение списка книг")
    public Response getBooks() {
        return request(null).get("/BookStore/v1/Books");
    }

    @Step("Получение пользователя")
    public Response getUser(String userId, String token) {
        return request(token).get("/Account/v1/User/{userId}", userId);
    }

    @Step("Добавление книги")
    public Response addBook(String userId, String token, String isbn) {
        return request(token)
                .body(AddBookRequest.of(userId, isbn))
                .post("/BookStore/v1/Books");
    }

    @Step("Удаление книги")
    public Response deleteBook(String userId, String token, String isbn) {
        return request(token)
                .queryParam("ISBN", isbn)
                .queryParam("UserId", userId)
                .delete("/BookStore/v1/Book");
    }

    @Step("Удаление всех книг пользователя")
    public Response deleteAllBooks(String userId, String token) {
        return request(token)
                .queryParam("UserId", userId)
                .delete("/BookStore/v1/Books");
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String userId, String token) {
        return request(token).delete("/Account/v1/User/{userId}", userId);
    }
}
