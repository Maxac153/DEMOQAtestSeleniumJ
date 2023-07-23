package pages;

import locators.BrowserWindowsLocators;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class BrowserWindowsPage {
    public static String clickButtonNewTab(WebDriver driver) {
        String result = "Error";
        String homePage = driver.getWindowHandle();
        driver.findElement(BrowserWindowsLocators.NEW_TAB).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            String windowTitle = driver.getTitle();
            if (!windowTitle.equals("DEMOQA")) {
                result = driver.findElement(BrowserWindowsLocators.NEW_TAB_TEXT).getText();
                driver.close();
            }
        }
        driver.switchTo().window(homePage);
        return result;
    }

    public static String clickButtonNewWindow(WebDriver driver) {
        String result = "Error";
        String homePage = driver.getWindowHandle();
        driver.findElement(BrowserWindowsLocators.NEW_WINDOW).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            String windowTitle = driver.getTitle();
            if (!windowTitle.equals("DEMOQA")) {
                result = driver.findElement(BrowserWindowsLocators.NEW_WINDOW_TEXT).getText();
                driver.close();
            }
        }
        driver.switchTo().window(homePage);
        return result;
    }

    public static String clickButtonNewWindowMessage(WebDriver driver) {
        String result = "Error";
        String homePage = driver.getWindowHandle();
        driver.findElement(BrowserWindowsLocators.NEW_WINDOW_MESSAGE).click();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            String windowTitle = driver.getTitle();
            if (!windowTitle.equals("DEMOQA")) {
                result = driver.findElement(BrowserWindowsLocators.NEW_WINDOW_MESSAGE_TEXT).getText();
                driver.close();
            }
        }
        driver.switchTo().window(homePage);
        return result;
    }
}
