package ui.demoqa.selenium.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(AllureReportExtension.class)
public abstract class BaseTest {
    protected WebDriver driver;

    // 1. Инициализируем Dotenv с мягкими настройками (не падает, если файла .env нет)
    protected static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    // 2. Вспомогательный метод для приоритетного чтения: снаружи (.env) -> система -> дефолт
    protected String getEnv(String key, String defaultValue) {
        String value = dotenv.get(key);
        if (value == null) {
            value = System.getenv(key);
        }
        return value != null ? value : defaultValue;
    }

    @BeforeEach
    void setUp() {
        // 3. Заменяем старые вызовы System.getenv() на наш универсальный метод
        String browserName = getEnv("BROWSER", "chrome").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(getEnv("HEADLESS", "false"));
        String windowSize = getEnv("WINDOW_SIZE", "full");

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--start-maximized");
                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("performance", "ALL");
                chromeOptions.setCapability("goog:loggingPrefs", chromePrefs);
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                String devNull = System.getProperty("os.name").toLowerCase().contains("win") ? "NUL" : "/dev/null";
                System.setProperty("webdriver.firefox.logfile", devNull);
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--start-maximized");
                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("performance", "ALL");
                edgeOptions.setCapability("ms:loggingPrefs", edgePrefs);
                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            case "safari":
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                driver = new SafariDriver(safariOptions);
                break;

            default:
                throw new IllegalArgumentException(String.format(
                        "Браузер '%s' не поддерживается. Выберите chrome, firefox, edge или safari.", browserName
                ));
        }

        applyWindowSize(driver, windowSize);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            isolateBrowserState(driver);
            try {
                driver.quit();
            } catch (WebDriverException e) {
                // Игнорируем исключения при закрытии браузера
            }
        }
    }

    private void applyWindowSize(WebDriver browser, String windowSize) {
        windowSize = (windowSize == null ? "full" : windowSize).toLowerCase();

        switch (windowSize) {
            case "mobile":
                browser.manage().window().setSize(new Dimension(390, 844));
                return;
            case "2k":
                browser.manage().window().setSize(new Dimension(1920, 1080));
                return;
            case "2.5k":
                browser.manage().window().setSize(new Dimension(2560, 1440));
                return;
            case "4k":
                browser.manage().window().setSize(new Dimension(3840, 2160));
                return;
            case "fullscreen":
            case "full":
                browser.manage().window().maximize();
                return;
        }

        if (windowSize.contains("x")) {
            try {
                String[] parts = windowSize.split("x", 2);
                int width = Integer.parseInt(parts[0]);
                int height = Integer.parseInt(parts[1]);
                browser.manage().window().setSize(new Dimension(width, height));
            } catch (NumberFormatException e) {
                browser.manage().window().maximize();
            }
        } else {
            browser.manage().window().maximize();
        }
    }

    private void isolateBrowserState(WebDriver browser) {
        dismissAlert(browser);
        closeExtraWindows(browser);
        clearBrowserState(browser);
    }

    private void dismissAlert(WebDriver browser) {
        try {
            Alert alert = browser.switchTo().alert();
            alert.dismiss();
        } catch (Exception e) {
            // Игнорируем
        }
    }

    private void closeExtraWindows(WebDriver browser) {
        try {
            java.util.Set<String> handles = browser.getWindowHandles();
            if (handles == null || handles.size() <= 1) return;

            String[] handleArray = handles.toArray(new String[0]);
            for (int i = 1; i < handleArray.length; i++) {
                browser.switchTo().window(handleArray[i]);
                browser.close();
            }
            browser.switchTo().window(handleArray[0]);
        } catch (Exception e) {
            // Игнорируем
        }
    }

    private void clearBrowserState(WebDriver browser) {
        try {
            browser.switchTo().defaultContent();
            browser.manage().deleteAllCookies();
            if (browser instanceof JavascriptExecutor) {
                JavascriptExecutor js = (JavascriptExecutor) browser;
                js.executeScript("window.localStorage.clear();");
                js.executeScript("window.sessionStorage.clear();");
            }
        } catch (Exception e) {
            // Игнорируем
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}