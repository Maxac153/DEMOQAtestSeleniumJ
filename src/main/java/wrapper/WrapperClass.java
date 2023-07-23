package wrapper;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import resources.TestConstants;

import java.io.ByteArrayInputStream;

public class WrapperClass {
    public static WebDriver driver;
    private static String url;

    public WrapperClass(String url) {
        this.url = url;
    }

    @BeforeTest
    public void setUp() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(this.url);
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static void screenshot() {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        byte[] screenshotBytes = screenshot.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Скриншот:", new ByteArrayInputStream(screenshotBytes));
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
