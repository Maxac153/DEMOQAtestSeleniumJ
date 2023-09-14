package locators;

import org.openqa.selenium.By;

public class ResizableLocators {
    public static final By RESIZABLE_BOX = By.xpath("//div[@id='resizableBoxWithRestriction']");
    public static final By RESIZABLE_BOX_HANDLE = By.xpath("//div[@id='resizableBoxWithRestriction']/span");
    public static final By RESIZABLE = By.xpath("//div[@id='resizable']");
    public static final By RESIZABLE_HANDLE = By.xpath("//div[@id='resizable']/span");
}
