package locators;

import org.openqa.selenium.By;

public class ModalDialogsLocators {
    public static By LARGE_MODAL_BUTTON = By.xpath("//button[@id='showLargeModal']");
    public static By SMALL_MODAL = By.xpath("//button[@id='showLargeModal']");
    public static By SMALL_MODAL_BODY = By.xpath("//div[@class='modal-body']");
    public static By CLOSE_SMALL_BUTTON = By.xpath("//button[@id='closeSmallModal']");
    public static By SMALL_MODAL_BUTTON = By.xpath("//button[@id='showSmallModal']");
    public static By LARGE_MODAL = By.xpath("//button[@id='showLargeModal']");
    public static By LARGE_MODAL_BODY = By.xpath("//div[@class='modal-body']/p");
    public static By CLOSE_LARGE_MODAL_BODY = By.xpath("//button[@id='closeLargeModal']");
}
