package ui.demoqa.selenium.tests.elements;

import __common.EndpointsDemoqa;
import __common.TestFaker;
import io.qameta.allure.*;
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

    @Test
    @DisplayName("Форма Text Box отображает введённые данные")
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Отправка формы Text Box")
    @Description("Проверка, что после отправки формы введённое имя отображается в блоке вывода")
    public void testTextBoxShowsSubmittedData() {
        String name = TestFaker.get().name().fullName();

        TextBoxPage textBoxPage = new TextBoxPage(driver, URL);
        textBoxPage.open();
        textBoxPage.fill(
                name,
                TestFaker.get().internet().emailAddress(),
                TestFaker.get().address().fullAddress(),
                TestFaker.get().address().fullAddress()
        );
        textBoxPage.submit();

        String shown = textBoxPage.outputName();

        Allure.step("Проверить, что в выводе отображается введённое имя", () -> assertTrue(shown.contains(name),
                String.format("Ожидалось, что вывод содержит имя '%s', но получен '%s'", name, shown)));
    }
}