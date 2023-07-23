package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProgressBarPage;
import resources.TestConstants;
import wrapper.WrapperClass;

import java.io.ByteArrayInputStream;

@Epic("Тестирование индекатора")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestProgressBar extends WrapperClass {
    public TestProgressBar() {
        super("https://demoqa.com/progress-bar");
    }

    @Test(priority = 1)
    @Feature("Проверка загрузки индикатора")
    public void TestProgressBar() {
        try {
            String result = ProgressBarPage.checkProgressBar(driver);
            String expectedResult = "100";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {

        } finally {
            screenshot();
        }
    }
}
