package locators;

import org.openqa.selenium.By;

public class AlertsLocators {
    public static By ALERT_BUTTON = By.xpath("//button[@id='alertButton']");
    public static By TIMER_ALERT_BUTTON = By.xpath("//button[@id='timerAlertButton']");
    public static By CONFIRM_BUTTON = By.xpath("//button[@id='confirmButton']");
    public static By CONFIRM_TEXT = By.xpath("//span[@id='confirmResult']");
    public static By PROMT_BUTTON = By.xpath("//button[@id='promtButton']");
    public static By PROMT_TEXT = By.xpath("//span[@id='promptResult']");
}
