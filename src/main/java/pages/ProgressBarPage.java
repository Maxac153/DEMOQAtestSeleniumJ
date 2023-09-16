package pages;

import locators.ProgressBarLocators;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class ProgressBarPage {
    public static String clickProgressBar(WebDriver driver) throws InterruptedException {
        driver.findElement(ProgressBarLocators.START_BUTTON).click();
        Thread.sleep(10000);
        return driver.findElement(ProgressBarLocators.PROGRESS_BAR).getAttribute("aria-valuenow");
    }
}
