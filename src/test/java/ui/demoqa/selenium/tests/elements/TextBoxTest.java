package ui.demoqa.selenium.tests.elements;


import __common.EndpointsDemoqa;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import net.datafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.demoqa.selenium.common.BaseTest;
import ui.demoqa.selenium.pages.elements.TextBoxPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("DEMOQA")
@Feature("Elements")
class TextBoxTest extends BaseTest {
    private static final String URL = dotenv.get("DEMOQA_HOST") + EndpointsDemoqa.TEXT_BOX.getUrl();
    // TODO убрать Faker в BaseTest
    private static final Faker FAKER = new Faker();

    @Test
    @Tag("smoke")
    @Story("Text Box")
    @DisplayName("Форма Text Box отображает введённые данные")
    void shouldShowSubmittedData() {
        String name = FAKER.name().fullName();

        TextBoxPage page = new TextBoxPage(driver, URL);
        page.open();
        page.fill(
                name,
                FAKER.internet().emailAddress(),
                FAKER.address().fullAddress(),
                FAKER.address().fullAddress()
        );
        page.submit();
        String shown = page.outputName();

        assertTrue(shown.contains(name), "В выводе нет имени: " + shown);
    }
}
