package utils.runner;

import utils.LoggerUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigProperties {
    private static Properties properties = initProperties();

    public static final Map<String, String> ENVIRONMENT_CHROMIUM = setEnvironment("browserName1", "isHeadLess1", "slowMo1");
    public static final Map<String, String> ENVIRONMENT_FIREFOX = setEnvironment("browserName2", "isHeadLess2", "slowMo2");
    public static final Map<String, String> ENVIRONMENT_WEBKIT = setEnvironment("browserName3", "isHeadLess3", "slowMo3");

    private static Properties initProperties() {
        properties = new Properties();

        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");

            properties.load(file);
        } catch (IOException e) {
            LoggerUtils.logError("ERROR: Properties file NOT found OR file IS EMPTY OR file IS CORRUPT.");
        }
        return properties;
    }

    private static Map<String, String> setEnvironment(String browser, String isHeadless, String slowMo) {
        Map<String, String> environment = new HashMap<>();


        if (properties != null && !properties.isEmpty()
                && properties.containsKey(browser) && !properties.getProperty(browser).trim().isEmpty()
                && properties.containsKey(isHeadless) && !properties.getProperty(isHeadless).trim().isEmpty()
                && properties.containsKey(slowMo) && !properties.getProperty(slowMo).trim().isEmpty()
        ) {
            environment.put("browser", properties.getProperty(browser).trim());
            environment.put("isHeadless", properties.getProperty(isHeadless).trim());
            environment.put("slowMo", properties.getProperty(slowMo).trim());
        } else {
            LoggerUtils.logWarning("WARNING: Set DEFAULT environment.");

            environment.put("browser", "chromium");
            environment.put("isHeadless", "true");
            environment.put("slowMo", "0");
        }

        return environment;
    }
}