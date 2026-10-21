package api.demoqa.rest_assured.tests;

import api.demoqa.rest_assured.common.BaseApiTest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("API-rest-assured-test")
@Feature("Аккаунт")
public class AccountApiTest extends BaseApiTest {

    @Test
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("positive"), @Tag("account")})
    @Story("Генерация токена")
    @Description("Проверка генерации токена")
    public void testGenerateToken() {
        Allure.step("Проверка наличия токена", () -> {
            assertNotNull(token, "Токен отсутствует");
            assertFalse(token.isBlank(), "Токен отсутствует");
        });
    }

    @Test
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("positive"), @Tag("account")})
    @Story("Авторизация пользователя")
    @Description("Проверка успешной авторизации пользователя")
    public void testAuthorized() {
        Response response = Allure.step("Авторизация пользователя",
                () -> client.authorized(userName, password));

        Allure.step("Проверка статус-кода ответа", () ->
                assertEquals(200, response.statusCode(), String.format(
                        "Ожидался статус-код 200, получен %d. Тело ответа: %s",
                        response.statusCode(), response.asString())));

        Allure.step("Проверка результата авторизации", () -> {
            boolean authorized = Boolean.parseBoolean(response.asString().trim());
            assertTrue(authorized, "Ожидался результат авторизации true, получен: " + response.asString());
        });
    }
}
