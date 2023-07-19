package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProgressBarPage;
import resources.TestConstants;

@Epic("Тестирование индекатора")
@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class TestProgressBar {

    @Test
    @Feature("Проверка загрузки индикатора")
    public void TestProgressBar() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/progress-bar");
            String result = ProgressBarPage.checkProgressBar(driver);
            String expectedResult = "100";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }

    }
}
