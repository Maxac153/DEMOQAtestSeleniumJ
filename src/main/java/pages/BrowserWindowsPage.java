package pages;

import locators.BrowserWindowsLocators;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class BrowserWindowsPage {
    public static String clickButtonNewTab(WebDriver driver) {
        driver.findElement(BrowserWindowsLocators.NEW_TAB).click();
        driver.switchTo().window("demoqa.com/sample");
        return driver.findElement(BrowserWindowsLocators.NEW_TAB_TEXT).getText();
    }

    public static String clickButtonNewWindow(WebDriver driver) {
        driver.findElement(BrowserWindowsLocators.NEW_WINDOW).click();
        driver.switchTo().window("demoqa.com/sample");
        return driver.findElement(BrowserWindowsLocators.NEW_WINDOW_TEXT).getText();
    }

    public static String clickButtonNewWindowMessage(WebDriver driver) {
        driver.findElement(BrowserWindowsLocators.NEW_WINDOW_MESSAGE).click();
        driver.switchTo().window("demoqa.com/sample");
        return driver.findElement(BrowserWindowsLocators.NEW_WINDOW_MESSAGE_TEXT).getText();
    }
}
