package locators;

import org.openqa.selenium.By;

public class DroppableLocators {
    public static By DRAG_ME_ONE = By.xpath("//div[@id='draggable']");
    public static By DRAG_HERE = By.xpath("//div[@id='droppable']");
    public static By BUTTON_PREVENT_PROPOGATION = By.xpath("//a[text()='Prevent Propogation']");
    public static By DRAG_ME_TWO = By.xpath("//div[@id='dragBox']");
    public static By OUTER_DROPPABLE = By.xpath("//div[@id='notGreedyDropBox']");
    public static By INNER_DROPPABLE = By.xpath("//div[@id='notGreedyInnerDropBox']");
}
