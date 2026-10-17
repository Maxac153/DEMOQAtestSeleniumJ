package ui.demoqa.selenium.common;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.ByteArrayInputStream;

public class AllureReportExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            if (driver != null) {
                try {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment(
                            "Снимок экрана при сбое (call)",
                            new ByteArrayInputStream(screenshot)
                    );
                } catch (WebDriverException e) {
                    // Игнорируем, если не удалось снять скриншот
                }
            }
        }
    }
}
