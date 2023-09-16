package pages;

import locators.ResizableLocators;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

public class ResizablePage {
    public static Dimension clickResizableBox(WebDriver driver, int x, int y) {
        WebElement element = driver.findElement(ResizableLocators.RESIZABLE_BOX);
        WebElement element1 = driver.findElement(ResizableLocators.RESIZABLE_BOX_HANDLE);
        Actions act = new Actions(driver);
        act.dragAndDropBy(element1, x, y).build().perform();
        return element.getSize();
    }

    public static Dimension clickResizable(WebDriver driver, int x, int y) {
        WebElement element = driver.findElement(ResizableLocators.RESIZABLE);
        WebElement element1 = driver.findElement(ResizableLocators.RESIZABLE_HANDLE);
        Actions act = new Actions(driver);
        act.dragAndDropBy(element1, x, y).build().perform();
        return element.getSize();
    }
}
