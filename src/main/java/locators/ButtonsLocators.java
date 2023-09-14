package locators;

import org.openqa.selenium.By;

public class ButtonsLocators {
    public static final By DOUBLE_CLICK = By.xpath("//button[@id='doubleClickBtn']");
    public static final By TEXT_DOUBLE_CLICK = By.xpath("//p[@id='doubleClickMessage']");
    public static final By RIGHT_CLICK = By.xpath("//button[@id='rightClickBtn']");
    public static final By TEXT_RIGHT_CLICK = By.xpath("//p[@id='rightClickMessage']");
    public static final By CLICK_ME = By.xpath("//button[text()='Click Me']");
    public static final By TEXT_CLICK_ME = By.xpath("//p[@id='dynamicClickMessage']");
}
