package pages;

import locators.SelectMenuLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrapper.WrapperClass;

public class SelectMenuPage {
    private static By getSelectValueLocator(String selectElement) {
        By result;
        switch (selectElement) {
            case "Group 1, option 1":
                result = SelectMenuLocators.SELECT_GROUP_ONE_OPTION_ONE;
                break;
            case "Group 1, option 2":
                result = SelectMenuLocators.SELECT_GROUP_ONE_OPTION_TWO;
                break;
            case "Group 2, option 1":
                result = SelectMenuLocators.SELECT_GROUP_TWO_OPTION_ONE;
                break;
            case "Group 2, option 2":
                result = SelectMenuLocators.SELECT_GROUP_TWO_OPTION_TWO;
                break;
            case "A root option":
                result = SelectMenuLocators.SELECT_A_ROOT_OPTION;
                break;
            default:
                result = SelectMenuLocators.SELECT_ANOTHER_ROOT_OPTION;
        }
        return result;
    }

    public static String selectValue(WebDriver driver, String selectElement) {
        WebElement select_value = driver.findElements(SelectMenuLocators.SELECTS).get(0);
        select_value.click();
        driver.findElement(getSelectValueLocator(selectElement)).click();
        return select_value.getText();
    }

    private static By getSelectOneLocator(String selectElement) {
        By result;
        switch (selectElement) {
            case "Dr.":
                result = SelectMenuLocators.SELECT_DR;
                break;
            case "Mr.":
                result = SelectMenuLocators.SELECT_MR;
                break;
            case "Mrs.":
                result = SelectMenuLocators.SELECT_MRS;
                break;
            case "Ms.":
                result = SelectMenuLocators.SELECT_MS;
                break;
            case "Prof.":
                result = SelectMenuLocators.SELECT_PROF;
                break;
            default:
                result = SelectMenuLocators.SELECT_OTHER;
        }
        return result;
    }

    public static String selectOne(WebDriver driver, String selectElement) {
        WebElement select_one = driver.findElements(SelectMenuLocators.SELECTS).get(1);
        select_one.click();
        driver.findElement(getSelectOneLocator(selectElement)).click();
        return select_one.getText();
    }

    public static String selectOldStyleMenu() {
        return "";
    }

    public static String selectMultiselectDropDown() {
        // WebElement select_value = driver.findElements(SelectMenuLocators.SELECTS).get(2);
        return "";
    }

    public static String deleteItemMultiselectDropDown() {
        // WebElement select_value = driver.findElements(SelectMenuLocators.SELECTS).get(2);
        return "";
    }

    public static String selectStandardMulti() {
        return "";
    }
}
