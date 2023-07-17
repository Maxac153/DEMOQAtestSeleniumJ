package locators;

import org.openqa.selenium.By;

public class SortableLocators {
    public static By LIST = By.xpath("//div[@class='vertical-list-container mt-4']");
    public static By TOW_ITEM = By.xpath("//div[text()='Two']");
    public static By FOUR_ITEM = By.xpath("//div[text()='Four']");
    public static By BUTTON_GRID = By.xpath("//a[text()='Grid']");
    public static By GRID = By.xpath("//div[@class='create-grid']");
    public static By GRID_ITEM_TOW = By.xpath("//div[@class='create-grid']/div[text()='Two']");
    public static By GRID_ITEM_EIGHT = By.xpath("//div[@class='create-grid']/div[text()='Eight']");
}
