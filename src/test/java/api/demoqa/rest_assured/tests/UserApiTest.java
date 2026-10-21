package api.demoqa.rest_assured.tests;


import api.demoqa.rest_assured.common.BaseApiTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Epic("API-rest-assured-test")
@Feature("Пользователь")
public class UserApiTest extends BaseApiTest {

    @Test
    @Tags({@Tag("api"), @Tag("smoke"), @Tag("positive")})
    @Story("Создание и авторизация пользователя")
    @Description("Создание пользователя и получение токена")
    public void testCreateUserAndGetToken() {
        Allure.step("Проверка данных созданного пользователя", () -> {
            assertNotNull(userId, "user_id отсутствует");
            assertFalse(userId.isBlank(), "user_id отсутствует");
            assertTrue(userName.startsWith("api_user_"), "Некорректный user_name");
            assertFalse(password.isBlank(), "password отсутствует");
        });

        Allure.step("Проверка токена", () -> {
            assertNotNull(token, "Токен отсутствует");
            assertFalse(token.isBlank(), "Токен отсутствует");
        });
    }
}
