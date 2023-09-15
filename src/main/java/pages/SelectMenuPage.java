package pages;

import locators.SelectMenuLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wrapper.WrapperClass;

public class SelectMenuPage {
    private static By getSelectValueElement(String selectElement) {
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
        WebElement select = driver.findElements(SelectMenuLocators.SELECTS).get(0);
        select.click();
        driver.findElement(getSelectValueElement(selectElement)).click();
        return select.getText();
    }

    public static String selectOne(WebDriver driver, Integer item) {
        WebElement select_one = driver.findElements(SelectMenuLocators.SELECTS).get(1);

        return "";
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
