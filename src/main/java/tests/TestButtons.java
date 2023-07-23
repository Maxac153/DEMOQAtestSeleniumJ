package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ButtonsPage;
import resources.TestConstants;
import wrapper.WrapperClass;

import java.io.ByteArrayInputStream;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Тестирование кнопок")
public class TestButtons extends WrapperClass {
    public TestButtons() {
        super("https://demoqa.com/buttons");
    }

    @Test(priority = 1)
    @Feature("Проверка двойного нажатия")
    public void testDoubleClick() {
        String result = ButtonsPage.checkDoubleClickMe(driver);
        String expectedResult = "You have done a double click";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            driver.close();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка нажатия правой кнопки")
    public void testRightClick() {
        String result = ButtonsPage.checkRightClickMe(driver);
        String expectedResult = "You have done a right click";
        Assert.assertEquals(result, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 3)
    @Feature("Проверка простого нажатия")
    public void testClick() {
        String result = ButtonsPage.checkClickMe(driver);
        String expectedResult = "You have done a dynamic click";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
