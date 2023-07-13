package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import locators.AlertsLocators;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsPage {
    public static String clickButtonToSeeAlert(WebDriver driver) {
        driver.findElement(AlertsLocators.ALERT_BUTTON).click();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.alertIsPresent()
        );
        String result = driver.switchTo().alert().getText();
        alert.accept();
        return result;
    }

    public static String clickButtonToSeeAlertAfterFiveSeconds(WebDriver driver) {
        driver.findElement(AlertsLocators.TIMER_ALERT_BUTTON).click();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.alertIsPresent()
        );
        String result = driver.switchTo().alert().getText();
        alert.accept();
        return result;
    }

    public static String clickButtonToSeeConfirmBox(WebDriver driver) {
        driver.findElement(AlertsLocators.CONFIRM_BUTTON).click();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.alertIsPresent()
        );
        driver.switchTo().alert().getText();
        alert.dismiss();
        return driver.findElement(AlertsLocators.CONFIRM_TEXT).getText();
    }

    public static String clickButtonToSeePromtBox(WebDriver driver) {
        driver.findElement(AlertsLocators.PROMT_BUTTON).click();
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.alertIsPresent()
        );
        driver.switchTo().alert().getText();
        alert.sendKeys("Test text");
        alert.accept();
        return driver.findElement(AlertsLocators.PROMT_TEXT).getText();
    }
}
