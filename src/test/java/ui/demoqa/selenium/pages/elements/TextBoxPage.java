package ui.demoqa.selenium.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.demoqa.selenium.common.BasePage;
import ui.demoqa.selenium.locators.elements.TextBoxLocators;

public class TextBoxPage extends BasePage {
    public TextBoxPage(WebDriver driver, String URL) {
        super(driver, URL);
    }

    @Step("Заполнить форму: {name}, {mail}")
    public TextBoxPage fill(
            String name,
            String mail,
            String current,
            String permanent
    ) {
        driver.findElement(TextBoxLocators.fullName).sendKeys(name);
        driver.findElement(TextBoxLocators.email).sendKeys(mail);
        driver.findElement(TextBoxLocators.currentAddress).sendKeys(current);
        driver.findElement(TextBoxLocators.permanentAddress).sendKeys(permanent);

        return this;
    }

    @Step("Нажать Submit")
    public TextBoxPage submit() {
        var button = getWait().until(ExpectedConditions.elementToBeClickable(TextBoxLocators.submit));
        goToElement(button);
        button.click();

        return this;
    }

    public String outputName() {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(TextBoxLocators.outputName)).getText();
    }
}