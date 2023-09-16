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

    public static String getValueColor(String selectElement) {
        String result;
        switch (selectElement) {
            case "Red":
                result = "red";
                break;
            case "Blue":
                result = "1";
                break;
            case "Green":
                result = "2";
                break;
            case "Yellow":
                result = "3";
                break;
            case "Purple":
                result = "4";
                break;
            case "Black":
                result = "5";
                break;
            case "White":
                result = "6";
                break;
            case "Voilet":
                result = "7";
                break;
            case "Indigo":
                result = "8";
                break;
            case "Magenta":
                result = "9";
                break;
            default:
                result = "10";
        }
        return result;
    }

    public static String selectOldStyleMenu(WebDriver driver, String selectElement) {
        WebElement select_old_style_select = driver.findElement(SelectMenuLocators.OLD_STYLE_SELECT);
        select_old_style_select.sendKeys(getValueColor(selectElement));
        return select_old_style_select.getText();
    }

    public static By getMultiselectLocator(String selectElement) {
        By result;
        switch (selectElement) {
            case "Green":
                result = SelectMenuLocators.SELECT_GREEN;
                break;
            case "Black":
                result = SelectMenuLocators.SELECT_BLACK;
                break;
            case "Blue":
                result = SelectMenuLocators.SELECT_BLUE;
                break;
            default:
                result = SelectMenuLocators.SELECT_RED;
        }
        return result;
    }

    public static String selectMultiselectDropDown(WebDriver driver, String selectElement) {
        WebElement multiselect = driver.findElements(SelectMenuLocators.SELECTS).get(2);
        multiselect.click();
        driver.findElement(getMultiselectLocator(selectElement)).click();
        return multiselect.getText();
    }

    public static By getDeleteItemLocator(String removeItemName) {
        By result;
        switch (removeItemName) {
            case "Green":
                result = SelectMenuLocators.REMOVE_GREEN;
                break;
            case "Black":
                result = SelectMenuLocators.REMOVE_BLUE;
                break;
            case "Blue":
                result = SelectMenuLocators.REMOVE_BLACK;
                break;
            default:
                result = SelectMenuLocators.REMOVE_RED;
        }
        return result;
    }

    public static String deleteItemMultiselectDropDown(WebDriver driver, String removeItemName) {
        driver.findElement(getDeleteItemLocator(removeItemName)).click();
        return driver.findElements(SelectMenuLocators.SELECTS).get(2).getText();
    }

    public static String deleteAllItemMultiselectDropDown(WebDriver driver) {
        driver.findElement(SelectMenuLocators.REMOVE_ALL_COLOR).click();
        return driver.findElements(SelectMenuLocators.SELECTS).get(2).getText();
    }
}
