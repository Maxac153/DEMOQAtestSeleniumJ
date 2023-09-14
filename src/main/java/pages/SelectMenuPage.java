package pages;

import locators.SelectMenuLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrapper.WrapperClass;

public class SelectMenuPage extends WrapperClass {
    public static String selectValue(WebDriver driver) {
        WebElement select_value = driver.findElements(SelectMenuLocators.SELECTS).get(0);
        return "";
    }

    public static String selectOne() {
        WebElement select_one = driver.findElements(SelectMenuLocators.SELECTS).get(1);
        return "";
    }

    public static String selectOldStyleMenu() {
        return "";
    }

    public static String selectMultiselectDropDown() {
        WebElement select_value = driver.findElements(SelectMenuLocators.SELECTS).get(2);
        return "";
    }

    public static String deleteItemMultiselectDropDown() {
        WebElement select_value = driver.findElements(SelectMenuLocators.SELECTS).get(2);
        return "";
    }

    public static String selectStandardMulti() {
        return "";
    }
}
