package utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesOperations {

    private static Properties prop = new Properties();

    static {
        try {
            InputStream input = PropertiesOperations.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            if (input == null) {
                throw new RuntimeException("config.properties not found in resources!");
            }
            prop.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getPropertyValueByKey(String key) {
        return prop.getProperty(key);
    }
}
