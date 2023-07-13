package pages;

import locators.TextBoxLocators;
import org.openqa.selenium.WebDriver;

public class TextBoxPage {
    public static String checkTextBox(
            WebDriver driver, String fullName, String Email, String currentAddress, String permanentAddress) {
        driver.findElement(TextBoxLocators.FULL_NAME).sendKeys(fullName);
        driver.findElement(TextBoxLocators.EMAIL).sendKeys(Email);
        driver.findElement(TextBoxLocators.CURRENT_ADDRESS).sendKeys(currentAddress);
        driver.findElement(TextBoxLocators.PERMANENT_ADDRESS).sendKeys(permanentAddress);
        driver.findElement(TextBoxLocators.BUTTON_SUBMIT).click();
        return driver.findElement(TextBoxLocators.RESULT_SUBMIT).getText();
    }
}
