package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DynamicPropertiesPage;
import resources.TestConstants;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
@Epic("Тестирование динамических свойст")
public class TestDynamicProperties {

    @Test
    @Feature("Проверка активности кнопки")
    public void TestButtonEnableAfter() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/dynamic-properties");
            String result = DynamicPropertiesPage.checkButtonEnableAfter(driver);
            String expectedResult = null;
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка цвета кнопки")
    public void TestColorChange() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/dynamic-properties");
            String result = DynamicPropertiesPage.checkColorChange(driver);
            String expectedResult = "rgba(220, 53, 69, 1)";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка появления кнопки")
    public void TestVisibleAfter() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/dynamic-properties");
            String result = DynamicPropertiesPage.checkVisibleAfter(driver);
            String expectedResult = "Visible After 5 Seconds";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
