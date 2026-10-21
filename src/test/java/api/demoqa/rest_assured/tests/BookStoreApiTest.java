package api.demoqa.rest_assured.tests;


import api.demoqa.rest_assured.api.DemoQaClient;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("API-rest-assured-test")
@Feature("Книжный магазин")
public class BookStoreApiTest {
    private DemoQaClient client;

    // Пользователь для этого теста не нужен, поэтому BaseApiTest не наследуем
    @BeforeEach
    public void initClient() {
        client = new DemoQaClient();
    }

    @Test
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("positive"), @Tag("book_store")})
    @Story("Получение списка книг")
    @Description("Получение всех книг из книжного магазина")
    public void testGetBooks() {
        Response response = Allure.step("Получение списка книг", () -> client.getBooks());

        Allure.step("Проверка статус-кода ответа", () ->
                assertEquals(200, response.statusCode(), String.format(
                        "Ожидался статус-код 200, получен %d. Тело ответа: %s",
                        response.statusCode(), response.asString())));

        Object books = response.jsonPath().get("books");

        Allure.step("Проверка наличия списка книг в ответе", () ->
                assertNotNull(books, "В ответе отсутствует поле 'books': " + response.asString()));

        Allure.step("Проверка типа списка книг", () ->
                assertInstanceOf(List.class, books, "Поле 'books' должно содержать список"));

        Allure.step("Проверка наличия книг", () ->
                assertFalse(((List<?>) books).isEmpty(), "Список книг не должен быть пустым"));
    }
}
