package config;

public final class Config {
    private Config() {}

    public static final String HOST = System.getenv().getOrDefault("DEMOQA_HOST", "https://demoqa.com");
}