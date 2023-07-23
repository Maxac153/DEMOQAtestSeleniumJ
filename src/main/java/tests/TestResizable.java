package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ResizablePage;
import resources.TestConstants;
import org.testng.Assert;
import wrapper.WrapperClass;

import java.io.ByteArrayInputStream;

@Epic("Тестирование изменения размера")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestResizable extends WrapperClass {
    public TestResizable() {
        super("https://demoqa.com/resizable");
    }

    @Test(priority = 1)
    @Feature("Проверка изменения размера в блоке")
    public void testResizableBox() {
        Dimension result = ResizablePage.checkResizableBox(driver, 300, 400);
        try {
            Assert.assertEquals(300, result.height);
            Assert.assertEquals(500, result.width);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка изменения размера без ограничения блока")
    public void testResizable() {
        Dimension result = ResizablePage.checkResizable(driver, 30, 50);
        try {
            Assert.assertEquals(250, result.height);
            Assert.assertEquals(230, result.width);
        } finally {
            screenshot();
        }
    }
}
