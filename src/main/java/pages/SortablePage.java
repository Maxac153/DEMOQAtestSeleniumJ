package pages;

import locators.SortableLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SortablePage {
    public static String sortableItem(WebDriver driver) {
        WebElement element1 = driver.findElement(SortableLocators.TOW_ITEM);
        WebElement element2 = driver.findElement(SortableLocators.FOUR_ITEM);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(element1, element2).build().perform();
        return driver.findElement(SortableLocators.LIST).getText();
    }

    public static String sortableGrid(WebDriver driver) {
        driver.findElement(SortableLocators.BUTTON_GRID).click();
        WebElement element1 = driver.findElement(SortableLocators.GRID_ITEM_TOW);
        WebElement element2 = driver.findElement(SortableLocators.GRID_ITEM_EIGHT);
        Actions actions = new Actions(driver);
        actions.dragAndDrop(element1, element2).build().perform();
        return driver.findElement(SortableLocators.GRID).getText();
    }
}
