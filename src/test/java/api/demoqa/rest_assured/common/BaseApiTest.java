package api.demoqa.rest_assured.common;

import __common.BaseCommonTest;
import api.demoqa.rest_assured.api.DemoQaClient;
import api.demoqa.rest_assured.models.CreateUserResponse;
import api.demoqa.rest_assured.models.TokenResponse;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseApiTest extends BaseCommonTest {
    // Dotenv с мягкими настройками (не падает, если файла .env нет)
    protected static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    protected DemoQaClient client;
    protected String userId;
    protected String userName;
    protected String password;
    protected String token;

    @BeforeEach
    public void createUserAndToken() {
        client = new DemoQaClient();

        userName = "api_user_" + UUID.randomUUID().toString().substring(0, 8);
        password = "Aa1!" + UUID.randomUUID().toString().substring(0, 8);

        Allure.step("Создать пользователя через API", () -> {
            Response response = client.createUser(userName, password);
            assertEquals(201, response.statusCode(),
                    "Не удалось создать пользователя. Тело ответа: " + response.asString());
            userId = response.as(CreateUserResponse.class).userID();
        });

        Allure.step("Сгенерировать токен", () -> {
            Response response = client.generateToken(userName, password);
            assertEquals(200, response.statusCode(),
                    "Не удалось получить токен. Тело ответа: " + response.asString());
            token = response.as(TokenResponse.class).token();
        });
    }

    @AfterEach
    public void deleteUser() {
        if (userId == null || token == null) {
            return;
        }
        Allure.step("Удалить созданного пользователя", () -> client.deleteUser(userId, token));
    }
}