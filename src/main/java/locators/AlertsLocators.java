package locators;

import org.openqa.selenium.By;

public class AlertsLocators {
    public static final By ALERT_BUTTON = By.xpath("//button[@id='alertButton']");
    public static final By TIMER_ALERT_BUTTON = By.xpath("//button[@id='timerAlertButton']");
    public static final By CONFIRM_BUTTON = By.xpath("//button[@id='confirmButton']");
    public static final By CONFIRM_TEXT = By.xpath("//span[@id='confirmResult']");
    public static final By PROMT_BUTTON = By.xpath("//button[@id='promtButton']");
    public static final By PROMT_TEXT = By.xpath("//span[@id='promptResult']");
}
