package locators;

import org.openqa.selenium.By;

public class ButtonsLocators {
    public static By DOUBLE_CLICK = By.xpath("//button[@id='doubleClickBtn']");
    public static By TEXT_DOUBLE_CLICK = By.xpath("//p[@id='doubleClickMessage']");
    public static By RIGHT_CLICK = By.xpath("//button[@id='rightClickBtn']");
    public static By TEXT_RIGHT_CLICK = By.xpath("//p[@id='rightClickMessage']");
    public static By CLICK_ME = By.xpath("//button[text()='Click Me']");
    public static By TEXT_CLICK_ME = By.xpath("//p[@id='dynamicClickMessage']");
}
