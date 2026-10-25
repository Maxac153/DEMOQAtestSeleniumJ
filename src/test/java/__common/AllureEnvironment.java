package __common;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public final class AllureEnvironment {

    private static final AtomicBoolean WRITTEN = new AtomicBoolean(false);

    private AllureEnvironment() {
    }

    /**
     * Можно вызывать из любого потока и любого BaseTest: запишет только первый вызов.
     */
    public static void write() {
        if (!WRITTEN.compareAndSet(false, true)) {
            return;
        }

        Map<String, String> env = new LinkedHashMap<>();
        env.put("OS", System.getProperty("os.name"));
        env.put("System execution", System.getProperty("os.name") + " "
                + System.getProperty("os.version") + " " + System.getProperty("os.arch"));
        env.put("Environment", "QA");
        env.put("DEMOQA_HOST", "https://demoqa.com");
        env.put("OS_SYSTEM", System.getProperty("os.name"));
        env.put("OS_VERSION", System.getProperty("os.version"));
        env.put("MACHINE", System.getProperty("os.arch"));
        env.put("JAVA_VERSION", System.getProperty("java.version"));

        Properties props = new Properties();
        env.forEach(props::setProperty);

        try {
            Path dir = Paths.get(
                    System.getProperty("allure.results.directory", "target/allure-results"));
            Files.createDirectories(dir);

            Path tmp = Files.createTempFile(dir, "environment", ".tmp");
            try (OutputStream out = Files.newOutputStream(tmp)) {
                props.store(out, null);
            }
            Files.move(tmp, dir.resolve("environment.properties"),
                    StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            WRITTEN.set(false);
            throw new RuntimeException("Не удалось записать Allure environment", e);
        }
    }
}