package locators;

import org.openqa.selenium.By;

public class SortableLocators {
    public static final By LIST = By.xpath("//div[@class='vertical-list-container mt-4']");
    public static final By TOW_ITEM = By.xpath("//div[text()='Two']");
    public static final By FOUR_ITEM = By.xpath("//div[text()='Four']");
    public static final By BUTTON_GRID = By.xpath("//a[text()='Grid']");
    public static final By GRID = By.xpath("//div[@class='create-grid']");
    public static final By GRID_ITEM_TOW = By.xpath("//div[@class='create-grid']/div[text()='Two']");
    public static final By GRID_ITEM_EIGHT = By.xpath("//div[@class='create-grid']/div[text()='Eight']");
}
