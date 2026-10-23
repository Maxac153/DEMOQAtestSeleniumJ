package __common;

import io.github.cdimascio.dotenv.Dotenv;
import net.datafaker.Faker;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Faker для всего прогона: один экземпляр на поток, все сиды выводятся из одного базового SEED.
 */
public final class TestFaker {
    private static final Dotenv DOTENV = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    // Нужна локаль со страной (ru-RU), иначе datafaker не может сгенерировать телефон.
    // Подчёркивание (ru_RU, как в Python-версии) приводим к формату BCP-47.
    public static final String LOCALE = read("FAKER_LOCALE", "ru-RU").replace('_', '-');
    public static final long SEED = Long.parseLong(
            read("FAKER_SEED", String.valueOf(new Random().nextInt(Integer.MAX_VALUE))));

    private static final Locale FAKER_LOCALE = Locale.forLanguageTag(LOCALE);
    private static final AtomicInteger THREAD_COUNTER = new AtomicInteger();

    private static final ThreadLocal<Faker> FAKER = ThreadLocal.withInitial(
            () -> new Faker(FAKER_LOCALE, new Random(SEED + THREAD_COUNTER.getAndIncrement())));

    private TestFaker() {
    }

    public static Faker get() {
        return FAKER.get();
    }

    private static String read(String key, String defaultValue) {
        String value = DOTENV.get(key);
        if (value == null) {
            value = System.getenv(key);
        }
        return value != null ? value : defaultValue;
    }
}