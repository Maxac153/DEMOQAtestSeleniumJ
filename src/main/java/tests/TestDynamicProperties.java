package tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DynamicPropertiesPage;
import resources.TestConstants;

import static org.junit.Assert.assertEquals;

public class TestDynamicProperties {
    @Test
    public void TestButtonEnableAfter() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/dynamic-properties");
            String result = DynamicPropertiesPage.checkButtonEnableAfter(driver);
            String expectedResult = null;
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    public void TestColorChange() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/dynamic-properties");
            String result = DynamicPropertiesPage.checkColorChange(driver);
            String expectedResult = "rgba(220, 53, 69, 1)";
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    public void TestVisibleAfter() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/dynamic-properties");
            String result = DynamicPropertiesPage.checkVisibleAfter(driver);
            String expectedResult = "Visible After 5 Seconds";
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
