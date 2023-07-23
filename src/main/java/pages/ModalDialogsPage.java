package pages;

import locators.ModalDialogsLocators;
import org.openqa.selenium.WebDriver;

public class ModalDialogsPage {
    public static String checkSmallModal(WebDriver driver) {
        driver.findElement(ModalDialogsLocators.SMALL_MODAL_BUTTON).click();
        String result = driver.findElement(ModalDialogsLocators.SMALL_MODAL_HEADER).getText();
        driver.findElement(ModalDialogsLocators.CLOSE_SMALL_BUTTON).click();
        return result;
    }

    public static String checkLargeModal(WebDriver driver) {
        driver.findElement(ModalDialogsLocators.LARGE_MODAL_BUTTON).click();
        String result = driver.findElement(ModalDialogsLocators.LARGE_MODAL_BODY).getText();
        driver.findElement(ModalDialogsLocators.CLOSE_LARGE_MODAL_BODY).click();
        return result;
    }
}
