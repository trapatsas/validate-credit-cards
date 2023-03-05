package org.validator.creditCard.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum ConfigurationManager {
    INSTANCE("/card_patterns.properties");

    private static final Logger logger;
    private final Properties prop;
    private final String propertiesFile;

    ConfigurationManager(String propsFile) {
        this.propertiesFile = propsFile;
        prop = new Properties();
        try (InputStream is = ConfigurationManager.class.getResourceAsStream(propsFile)) {
            prop.load(is);
        } catch (IOException e) {
            throw new RuntimeException(propertiesFile + " file could not be found.", e);
        }
    }

    public String getCreditCardPattern(String key) {
        String pattern = prop.getProperty(key);
        if (pattern == null) {
            throw new IllegalArgumentException(key + " key could not be found in " + propertiesFile);
        }
        return pattern;
    }

    // By moving the creation of the logger into a static block, we ensure that it is initialized after the enum constants are declared
    // This fixes the issue of accessing a static member from the enum constructor or instance initializer.
    static {
        logger = LogManager.getLogger(ConfigurationManager.class);
        logger.trace("Using properties file: {}", INSTANCE.propertiesFile);
    }
}
