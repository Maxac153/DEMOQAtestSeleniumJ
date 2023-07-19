package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ResizablePage;
import resources.TestConstants;
import org.testng.Assert;

@Epic("Тестирование изменения размера")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestResizable {

    @Test
    @Feature("Проверка изменения размера в блоке")
    public void testResizableBox() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/resizable");
            Dimension result = ResizablePage.checkResizableBox(driver, 300, 400);
            Assert.assertEquals(300, result.height);
            Assert.assertEquals(500, result.width);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка изменения размера без ограничения блока")
    public void testResizable() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/resizable");
            Dimension result = ResizablePage.checkResizable(driver, 300, 400);
            Assert.assertEquals(300, result.height);
            Assert.assertEquals(500, result.width);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
