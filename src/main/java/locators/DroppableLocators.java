package locators;

import org.openqa.selenium.By;

public class DroppableLocators {
    public static final By DRAG_ME_ONE = By.xpath("//div[@id='draggable']");
    public static final By DRAG_HERE = By.xpath("//div[@id='droppable']");
    public static final By DROPPABLE_TEXT = By.xpath("//div[@id='droppable']/p");
    public static final By BUTTON_PREVENT_PROPOGATION = By.xpath("//a[text()='Prevent Propogation']");
    public static final By DRAG_ME_TWO = By.xpath("//div[@id='dragBox']");
    public static final By OUTER_DROPPABLE_TEXT = By.xpath("//div[@id='notGreedyDropBox']/p");
    public static final By INNER_DROPPABLE_TEXT = By.xpath("//div[@id='notGreedyInnerDropBox']/p");
}
