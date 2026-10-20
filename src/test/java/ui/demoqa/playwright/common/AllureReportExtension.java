package ui.demoqa.playwright.common;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.ByteArrayInputStream;

public class AllureReportExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (context.getExecutionException().isEmpty()) {
            return;
        }
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof BaseTest) {
            Page page = ((BaseTest) testInstance).getPage();
            if (page != null && !page.isClosed()) {
                try {
                    byte[] screenshot = page.screenshot(
                            new Page.ScreenshotOptions().setFullPage(true)
                    );
                    Allure.addAttachment(
                            "Снимок экрана при сбое (call)",
                            new ByteArrayInputStream(screenshot)
                    );
                } catch (PlaywrightException e) {
                    // Игнорируем, если не удалось снять скриншот
                }
            }
        }
    }
}
