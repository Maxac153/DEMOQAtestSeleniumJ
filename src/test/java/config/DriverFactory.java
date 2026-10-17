package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class DriverFactory {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static WebDriver create() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--no-sandbox",
                "--disable-dev-shm-usage", "--window-size=1920,1080");
        WebDriver driver = new ChromeDriver(options); // Selenium Manager сам скачает драйвер
        DRIVER.set(driver);
        return driver;
    }

    public static WebDriver get() {
        return DRIVER.get();
    }

    public static void quit() {
        WebDriver d = DRIVER.get();
        if (d != null) {
            d.quit();
            DRIVER.remove();
        }
    }
}
