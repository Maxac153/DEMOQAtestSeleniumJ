package locators;

import org.openqa.selenium.By;

public class BrowserWindowsLocators {
    public static final By NEW_TAB = By.xpath("//button[@id='tabButton']");
    public static final By NEW_TAB_TEXT = By.xpath("//h1");
    public static final By NEW_WINDOW = By.xpath("//button[@id='windowButton']");
    public static final By NEW_WINDOW_TEXT = By.xpath("//h1");
    public static final By NEW_WINDOW_MESSAGE = By.xpath("//button[@id='messageWindowButton']");
    public static final By NEW_WINDOW_MESSAGE_TEXT = By.xpath("//body");
}
