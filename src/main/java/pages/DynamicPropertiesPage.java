package pages;

import locators.DynamicPropertiesLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DynamicPropertiesPage {
    public static String checkButtonEnableAfter(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.findElement(DynamicPropertiesLocators.BUTTON_ENABLE_AFTER).getAttribute("disabled");
    }

    public static String checkColorChange(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.findElement(DynamicPropertiesLocators.BUTTON_COLOR_CHANGE).getCssValue("color");
    }

    public static String checkVisibleAfter(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(DynamicPropertiesLocators.BUTTON_VISIBLE_AFTER));
        return element.getText();
    }
}
