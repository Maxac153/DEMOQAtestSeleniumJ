package ui.demoqa.playwright.pages.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import ui.demoqa.playwright.common.BasePage;
import ui.demoqa.playwright.locators.elements.TextBoxLocators;

public class TextBoxPage extends BasePage {
    public TextBoxPage(Page page, String URL) {
        super(page, URL);
    }

    @Step("Заполнить форму: {name}, {mail}")
    public void fill(
            String name,
            String mail,
            String current,
            String permanent
    ) {
        page.locator(TextBoxLocators.fullName).fill(name);
        page.locator(TextBoxLocators.email).fill(mail);
        page.locator(TextBoxLocators.currentAddress).fill(current);
        page.locator(TextBoxLocators.permanentAddress).fill(permanent);
    }

    @Step("Нажать Submit")
    public TextBoxPage submit() {
        Locator button = elementIsClickable(TextBoxLocators.submit);
        goToElement(button);
        button.click();

        return this;
    }

    public String outputName() {
        return elementIsVisible(TextBoxLocators.outputName).innerText();
    }
}