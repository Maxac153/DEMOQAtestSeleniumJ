package tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BrowserWindowsPage;
import resources.TestConstants;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestBrowserWindows {
    @Test
    public void TestNewTab() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://demoqa.com/browser-windows");
            String result = BrowserWindowsPage.clickButtonNewTab(driver);
            String expectedResult = "This is a sample page";
            assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
    }

    @Test
    public void TestNewWindow() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://demoqa.com/browser-windows");
            String result = BrowserWindowsPage.clickButtonNewWindow(driver);
            String expectedResult = "This is a sample page";
            assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
    }

    @Test
    public void TestNewWindowMessage() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://demoqa.com/browser-windows");
            String result = BrowserWindowsPage.clickButtonNewWindowMessage(driver);
            String expectedResult = "Knowledge increases by sharing but not by saving. Please share " +
                    "this website with your friends and in your organization.";
            assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            Set<String> windowHandles = driver.getWindowHandles();
            for (String handle : windowHandles) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
    }
}
