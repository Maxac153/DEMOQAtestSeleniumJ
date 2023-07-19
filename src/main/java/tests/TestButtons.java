package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ButtonsPage;
import resources.TestConstants;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Тестирование кнопок")
public class TestButtons {

    @Test
    @Feature("Проверка двойного нажатия")
    public void testDoubleClick() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/buttons");
            String result = ButtonsPage.checkDoubleClickMe(driver);
            String expectedResult = "You have done a double click";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка нажатия правой кнопки")
    public void testRightClick() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/buttons");
            String result = ButtonsPage.checkRightClickMe(driver);
            String expectedResult = "You have done a right click";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка простого нажатия")
    public void testClick() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/buttons");
            String result = ButtonsPage.checkClickMe(driver);
            String expectedResult = "You have done a dynamic click";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
