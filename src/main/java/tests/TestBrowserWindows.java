package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BrowserWindowsPage;
import resources.TestConstants;

import java.util.Set;

@Epic("Тестирование окон")
@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class TestBrowserWindows {

    @Test
    @Feature("Проверка новой вкладки")
    public void TestNewTab() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/browser-windows");
            String result = BrowserWindowsPage.clickButtonNewTab(driver);
            String expectedResult = "This is a sample page";
            Assert.assertEquals(expectedResult, result);
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
    @Feature("Проверка нового окна")
    public void TestNewWindow() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/browser-windows");
            String result = BrowserWindowsPage.clickButtonNewWindow(driver);
            String expectedResult = "This is a sample page";
            Assert.assertEquals(expectedResult, result);
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
    @Feature("Проверка нового окна с сообщением")
    public void TestNewWindowMessage() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/browser-windows");
            String result = BrowserWindowsPage.clickButtonNewWindowMessage(driver);
            String expectedResult = "Knowledge increases by sharing but not by saving. Please share " +
                    "this website with your friends and in your organization.";
            Assert.assertEquals(expectedResult, result);
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
