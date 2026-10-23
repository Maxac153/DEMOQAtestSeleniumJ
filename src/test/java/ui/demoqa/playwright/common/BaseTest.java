package ui.demoqa.playwright.common;

import com.microsoft.playwright.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

@ExtendWith(AllureReportExtension.class)
public abstract class BaseTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    protected Page page;
    private static final AtomicBoolean ENV_WRITTEN = new AtomicBoolean(false);

    // 1. Инициализируем Dotenv с мягкими настройками (не падает, если файла .env нет)
    protected static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    // 2. Вспомогательный метод для приоритетного чтения: снаружи (.env) -> система -> дефолт
    protected static String getEnv(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value == null) value = dotenv.get(key);
        if (value == null) value = System.getenv(key);
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

        writeAllureEnvironment(windowSize, isHeadless);
    }

    /**
     * Пишет environment.properties для Allure один раз за запуск (безопасно при параллельном запуске).
     */
    private void writeAllureEnvironment(String windowSize, boolean isHeadless) {
        if (!ENV_WRITTEN.compareAndSet(false, true)) return;

        try {
            Properties env = new Properties();
            env.setProperty("System execution", System.getProperty("os.name") + " "
                    + System.getProperty("os.version") + " " + System.getProperty("os.arch"));
            env.setProperty("Environment", getEnv("ENVIRONMENT", "QA"));
            env.setProperty("BROWSER", getEnv("BROWSER", "chrome"));
            env.setProperty("BROWSER_VERSION", orUnknown(browser.version()));
            env.setProperty("HEADLESS", String.valueOf(isHeadless));
            env.setProperty("DEMOQA_HOST", getEnv("DEMOQA_HOST", "unknown"));
            env.setProperty("WINDOW_SIZE", windowSize);
            env.setProperty("PLAYWRIGHT_VERSION", versionOf(Playwright.class));
            env.setProperty("JUNIT_VERSION", versionOf(org.junit.jupiter.api.Test.class));
            env.setProperty("ALLURE_VERSION", versionOf(io.qameta.allure.Allure.class));
            env.setProperty("OS_SYSTEM", System.getProperty("os.name"));
            env.setProperty("OS_VERSION", System.getProperty("os.version"));
            env.setProperty("MACHINE", System.getProperty("os.arch"));
            env.setProperty("JAVA_VERSION", System.getProperty("java.version"));

            Path resultsDir = Paths.get(System.getProperty("allure.results.directory", "target/allure-results"));
            Path reportHistoryDir = resultsDir.resolveSibling("allure-report").resolve("history");
            Path resultsHistoryDir = resultsDir.resolve("history");

            Files.createDirectories(resultsDir);

            // Перенос истории прошлого отчёта для трендов
            if (Files.isDirectory(reportHistoryDir)) {
                copyDirectory(reportHistoryDir, resultsHistoryDir);
            }

            try (Writer writer = Files.newBufferedWriter(
                    resultsDir.resolve("environment.properties"), StandardCharsets.UTF_8)) {
                env.store(writer, null);
            }
        } catch (Exception e) {
            // Отчёт об окружении не должен ронять тесты
        }
    }

    private static String orUnknown(String value) {
        return (value == null || value.isBlank()) ? "unknown" : value;
    }

    private static String versionOf(Class<?> clazz) {
        Package pkg = clazz.getPackage();
        return orUnknown(pkg == null ? null : pkg.getImplementationVersion());
    }

    private static void copyDirectory(Path source, Path target) throws IOException {
        try (Stream<Path> paths = Files.walk(source)) {
            for (Path path : (Iterable<Path>) paths::iterator) {
                Path dest = target.resolve(source.relativize(path).toString());
                if (Files.isDirectory(path)) {
                    Files.createDirectories(dest);
                } else {
                    Files.createDirectories(dest.getParent());
                    Files.copy(path, dest, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
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
