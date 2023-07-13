package locators;

import org.openqa.selenium.By;

public class TextBoxLocators {
    public static By FULL_NAME = By.xpath("//input[@id='userName']");
    public static By EMAIL = By.xpath("//input[@id='userEmail']");
    public static By CURRENT_ADDRESS = By.xpath("//textarea[@id='currentAddress']");
    public static By PERMANENT_ADDRESS = By.xpath("//textarea[@id='permanentAddress']");
    public static By BUTTON_SUBMIT = By.xpath("//button[@id='submit']");
    public static By RESULT_SUBMIT = By.xpath("//div[@id='output']/div");
}
