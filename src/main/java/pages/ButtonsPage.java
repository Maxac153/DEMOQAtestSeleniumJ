package pages;

import locators.ButtonsLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ButtonsPage {
    public static String checkDoubleClickMe(WebDriver driver) {
        WebElement element = driver.findElement(ButtonsLocators.DOUBLE_CLICK);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        return driver.findElement(ButtonsLocators.TEXT_DOUBLE_CLICK).getText();
    }

    public static String checkRightClickMe(WebDriver driver) {
        WebElement element = driver.findElement(ButtonsLocators.RIGHT_CLICK);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        return driver.findElement(ButtonsLocators.TEXT_RIGHT_CLICK).getText();
    }

    public static String checkClickMe(WebDriver driver) {
        driver.findElement(ButtonsLocators.CLICK_ME).click();
        return driver.findElement(ButtonsLocators.TEXT_CLICK_ME).getText();
    }
}
