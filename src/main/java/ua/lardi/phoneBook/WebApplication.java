package ua.lardi.phoneBook;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication(scanBasePackages = "ua.lardi.phoneBook")
public class WebApplication {
    private static final Logger LOGGER = LogManager.getLogger(WebApplication.class);

    public static void main(String[] args) {
        setUp();
        SpringApplication.run(WebApplication.class, args);
    }

    private static void setUp() {
        String path = System.getProperty("lardi.conf");

        if (path == null) {
            System.setProperty("GENERATED_KEY_NAME", "id");
            return;
        }

        //reading properties from config file
        Properties properties = new Properties();
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
            properties.load(stream);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        //setting config properties as system variables
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.setProperty((String) entry.getKey(), (String) entry.getValue());
        }

        //setting spring profile from properties
        String profile = properties.getProperty("profile");
        if (profile != null) {
            //System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
            System.setProperty("spring.profiles.default", profile);

            if (profile.equals("mysql")) {
                System.setProperty("GENERATED_KEY_NAME", "GENERATED_KEY");
            }
        }
    }
}
