package config;

public final class TestConfig {
    private TestConfig() {}

    public static final String HERO_URL = get("base.url", "http://localhost:3000/superheroes");
    public static final String ANIMAL_URL = get("animal.url", "http://localhost:3000/animal");

    private static String get(String key, String defaultValue) {
        return System.getProperty(key, defaultValue);
    }
}
