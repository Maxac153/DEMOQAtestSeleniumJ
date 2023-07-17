package locators;

import org.openqa.selenium.By;

public class ResizableLocators {
    public static By RESIZABLE_BOX = By.xpath("//div[@id='resizableBoxWithRestriction']");
    public static By RESIZABLE_BOX_HANDLE = By.xpath("//div[@id='resizableBoxWithRestriction']/span");
    public static By RESIZABLE = By.xpath("//div[@id='resizable']");
    public static By RESIZABLE_HANDLE = By.xpath("//div[@id='resizable']/span");
}
