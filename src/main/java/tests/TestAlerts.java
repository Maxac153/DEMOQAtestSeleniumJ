package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AlertsPage;
import resources.TestConstants;

@Epic("Тестирование оповещений")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestAlerts {
    @Test
    @Feature("Проверка простого оповещения")
    public void testAlertOne() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeeAlert(driver);
            String expectedResult = "You clicked a button";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка оповещения который появиться после 5 секунд")
    public void testAlertTwo() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeeAlertAfterFiveSeconds(driver);
            String expectedResult = "This alert appeared after 5 seconds";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка окна подтверждения")
    public void testAlertThree() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeeConfirmBox(driver);
            String expectedResult = "You selected Cancel";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка окна подсказки")
    public void testAlertFour() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeePromtBox(driver);
            String expectedResult = "You entered Test text";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
