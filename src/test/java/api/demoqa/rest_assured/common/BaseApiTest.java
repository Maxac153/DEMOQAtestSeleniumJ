package api.demoqa.rest_assured.common;

import api.demoqa.rest_assured.api.DemoQaClient;
import api.demoqa.rest_assured.models.CreateUserResponse;
import api.demoqa.rest_assured.models.TokenResponse;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseApiTest {
    private static final AtomicBoolean ENV_WRITTEN = new AtomicBoolean(false);

    // Dotenv с мягкими настройками (не падает, если файла .env нет)
    protected static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    // Приоритет: .env -> системные переменные -> значение по умолчанию
    protected static String getEnv(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value == null) value = dotenv.get(key);
        if (value == null) value = System.getenv(key);
        return value != null ? value : defaultValue;
    }

    protected DemoQaClient client;
    protected String userId;
    protected String userName;
    protected String password;
    protected String token;

    @BeforeEach
    public void createUserAndToken() {
        writeAllureEnvironment();

        client = new DemoQaClient();

        userName = "api_user_" + UUID.randomUUID().toString().substring(0, 8);
        password = "Aa1!" + UUID.randomUUID().toString().substring(0, 8);

        Allure.step("Создать пользователя через API", () -> {
            Response response = client.createUser(userName, password);
            assertEquals(201, response.statusCode(),
                    "Не удалось создать пользователя. Тело ответа: " + response.asString());
            userId = response.as(CreateUserResponse.class).userID();
        });

        Allure.step("Сгенерировать токен", () -> {
            Response response = client.generateToken(userName, password);
            assertEquals(200, response.statusCode(),
                    "Не удалось получить токен. Тело ответа: " + response.asString());
            token = response.as(TokenResponse.class).token();
        });
    }

    @AfterEach
    public void deleteUser() {
        if (userId == null || token == null) {
            return;
        }
        Allure.step("Удалить созданного пользователя", () -> client.deleteUser(userId, token));
    }

    /**
     * Пишет environment.properties для Allure один раз за запуск (безопасно при параллельном запуске).
     */
    private static void writeAllureEnvironment() {
        if (!ENV_WRITTEN.compareAndSet(false, true)) return;

        try {
            Properties env = new Properties();
            env.setProperty("System execution", System.getProperty("os.name") + " "
                    + System.getProperty("os.version") + " " + System.getProperty("os.arch"));
            env.setProperty("Environment", getEnv("ENVIRONMENT", "QA"));
            env.setProperty("DEMOQA_HOST", getEnv("DEMOQA_HOST", "https://demoqa.com"));
            env.setProperty("REST_ASSURED_VERSION", versionOf(RestAssured.class));
            env.setProperty("JUNIT_VERSION", versionOf(org.junit.jupiter.api.Test.class));
            env.setProperty("ALLURE_VERSION", versionOf(Allure.class));
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

    private static String versionOf(Class<?> clazz) {
        Package pkg = clazz.getPackage();
        String version = pkg == null ? null : pkg.getImplementationVersion();
        return (version == null || version.isBlank()) ? "unknown" : version;
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
}