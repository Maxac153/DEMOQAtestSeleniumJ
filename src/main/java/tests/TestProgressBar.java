package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import pages.ProgressBarPage;
import resources.TestConstants;

import static org.junit.Assert.assertEquals;

public class TestProgressBar {
    @Test
    public void TestProgressBar() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/progress-bar");
            String result = ProgressBarPage.checkProgressBar(driver);
            String expectedResult = "100";
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }

    }
}
