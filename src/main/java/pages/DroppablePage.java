package pages;

import locators.DroppableLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DroppablePage {
    public static String checkSimpleDragAndDrop(WebDriver driver) {
        new Actions(driver).clickAndHold(driver.findElement(DroppableLocators.DRAG_ME_ONE))
                           .moveToElement(driver.findElement(DroppableLocators.DRAG_HERE))
                           .release()
                           .build()
                           .perform();
        return driver.findElement(DroppableLocators.DROPPABLE_TEXT).getText();
    }

    public static String checkPreventPropogationDragAndDrop(WebDriver driver, int x, int y) {
        driver.findElement(DroppableLocators.BUTTON_PREVENT_PROPOGATION).click();
        new Actions(driver).dragAndDropBy(driver.findElement(DroppableLocators.DRAG_ME_TWO), x, y).perform();
        String result = driver.findElement(DroppableLocators.OUTER_DROPPABLE_TEXT).getText() + ' ' +
                        driver.findElement(DroppableLocators.INNER_DROPPABLE_TEXT).getText();
        return result;
    }
}
