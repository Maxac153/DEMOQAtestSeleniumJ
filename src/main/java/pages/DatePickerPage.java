package pages;

import locators.DatePickerLocators;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatePickerPage {
    public static String checkDate(WebDriver driver, String date) {
        WebElement inputElement = driver.findElement(DatePickerLocators.SELECT_DATE);
        inputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputElement.sendKeys(Keys.DELETE);
        inputElement.sendKeys(date);
        return inputElement.getAttribute("value");
    }

    public static String checkDateAndTime(WebDriver driver, String date) {
        WebElement inputElement = driver.findElement(DatePickerLocators.SELECT_DATE_AND_TIME);
        inputElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        inputElement.sendKeys(Keys.DELETE);
        inputElement.sendKeys(date);
        return inputElement.getAttribute("value");
    }
}
