package ui.demoqa.selenium.tests.elements;

import __common.EndpointsDemoqa;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ui.demoqa.selenium.common.BaseTest;
import ui.demoqa.selenium.pages.elements.TextBoxPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI-selenium-test")
@Feature("DemoQA")
@Story("Страница Text Box")
public class TextBoxTest extends BaseTest {
    private static final String URL = dotenv.get("DEMOQA_HOST") + EndpointsDemoqa.TEXT_BOX.getUrl();
    // TODO убрать Faker в BaseTest
    private static final Faker FAKER = new Faker();

    @Test
    @DisplayName("Форма Text Box отображает введённые данные")
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Отправка формы Text Box")
    @Description("Проверка, что после отправки формы введённое имя отображается в блоке вывода")
    public void testTextBoxShowsSubmittedData() {
        String name = FAKER.name().fullName();

        TextBoxPage textBoxPage = new TextBoxPage(driver, URL);
        textBoxPage.open();
        textBoxPage.fill(
                name,
                FAKER.internet().emailAddress(),
                FAKER.address().fullAddress(),
                FAKER.address().fullAddress()
        );
        textBoxPage.submit();

        String shown = textBoxPage.outputName();

        Allure.step("Проверить, что в выводе отображается введённое имя", () -> assertTrue(shown.contains(name),
                String.format("Ожидалось, что вывод содержит имя '%s', но получен '%s'", name, shown)));
    }
}