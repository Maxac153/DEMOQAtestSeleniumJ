package ui.demoqa.playwright.common;

import com.microsoft.playwright.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(AllureReportExtension.class)
public abstract class BaseTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    protected Page page;

    // 1. Инициализируем Dotenv с мягкими настройками (не падает, если файла .env нет)
    protected static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    // 2. Вспомогательный метод для приоритетного чтения: снаружи (.env) -> система -> дефолт
    protected static String getEnv(String key, String defaultValue) {
        String value = dotenv.get(key);
        if (value == null) {
            value = System.getenv(key);
        }
        return value != null ? value : defaultValue;
    }

    private Browser launchBrowser(Playwright pw, boolean isHeadless) {
        String browserName = getEnv("BROWSER", "chrome").toLowerCase();
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(isHeadless);
        List<String> args = List.of("--no-sandbox", "--start-maximized");

        return switch (browserName) {
            case "chrome" -> pw.chromium().launch(options.setArgs(args));
            case "edge" -> pw.chromium().launch(options.setChannel("msedge").setArgs(args));
            case "firefox" -> pw.firefox().launch(options);
            case "safari" -> pw.webkit().launch(options); // это WebKit, не настоящий Safari
            default -> throw new IllegalArgumentException(String.format(
                    "Браузер '%s' не поддерживается. Выберите chrome, firefox, edge или safari.", browserName));
        };
    }

    @BeforeEach
    void setUp() {
        String windowSize = getEnv("WINDOW_SIZE", "full");
        boolean isHeadless = Boolean.parseBoolean(getEnv("HEADLESS", "false"));

        playwright = Playwright.create();
        browser = launchBrowser(playwright, isHeadless);

        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        applyWindowSize(contextOptions, windowSize, isHeadless);

        context = browser.newContext(contextOptions);
        context.setDefaultNavigationTimeout(30_000);
        page = context.newPage();
    }

    @AfterEach
    void tearDown() {
        try {
            if (context != null) context.close();
        } finally {
            try {
                if (browser != null) browser.close();
            } finally {
                if (playwright != null) playwright.close();
            }
        }
    }

    private void applyWindowSize(
            Browser.NewContextOptions options,
            String windowSize,
            boolean isHeadless
    ) {
        windowSize = (windowSize == null ? "full" : windowSize).toLowerCase();

        switch (windowSize) {
            case "mobile":
                options.setViewportSize(390, 844);
                return;
            case "2k":
                options.setViewportSize(1920, 1080);
                return;
            case "2.5k":
                options.setViewportSize(2560, 1440);
                return;
            case "4k":
                options.setViewportSize(3840, 2160);
                return;
            case "fullscreen":
            case "full":
                maximize(options, isHeadless);
                return;
        }

        if (windowSize.contains("x")) {
            try {
                String[] parts = windowSize.split("x", 2);
                int width = Integer.parseInt(parts[0]);
                int height = Integer.parseInt(parts[1]);
                options.setViewportSize(width, height);
            } catch (NumberFormatException e) {
                maximize(options, isHeadless);
            }
        } else {
            maximize(options, isHeadless);
        }
    }

    private void maximize(Browser.NewContextOptions options, boolean isHeadless) {
        if (isHeadless) {
            options.setViewportSize(1920, 1080);
        } else {
            options.setViewportSize(null);
        }
    }

    public Page getPage() {
        return this.page;
    }
}
