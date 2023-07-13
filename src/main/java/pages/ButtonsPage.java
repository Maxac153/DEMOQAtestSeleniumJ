package pages;

import locators.ButtonsLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class ButtonsPage {
    public static String checkDoubleClickMe(WebDriver driver) {
        driver.findElement(ButtonsLocators.DOUBLE_CLICK).click();
        driver.findElement(ButtonsLocators.DOUBLE_CLICK).click();
        return driver.findElement(ButtonsLocators.TEXT_DOUBLE_CLICK).getText();
    }

    public static String checkRightClickMe(WebDriver driver) {
        driver.findElement(ButtonsLocators.RIGHT_CLICK).click();
        return driver.findElement(ButtonsLocators.TEXT_RIGHT_CLICK).getText();
    }

    public static String checkClickMe(WebDriver driver) {
        new Actions(driver).contextClick(driver.findElement(ButtonsLocators.CLICK_ME)).perform();
        return driver.findElement(ButtonsLocators.TEXT_CLICK_ME).getText();
    }
}
