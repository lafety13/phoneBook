package ua.goit.phoneBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class WebApplication {
    public static void main(String[] args) {
        setUp();
        SpringApplication.run(WebApplication.class, args);
    }

    private static void setUp() {
        String path = System.getProperty("lardi.conf");

        Properties properties = new Properties();
        InputStream stream = null;
        try {
            stream = new FileInputStream(path);
            properties.load(stream);
        } catch (FileNotFoundException e) {
            //logger
        } catch (IOException e) {
            //logger
        }

        //setting config properties as system variables
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            System.setProperty((String) entry.getKey(), (String) entry.getValue());
        }

        //setting spring profile from properties
        String profile = properties.getProperty("profile");
        if (profile != null) {
            System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
            if (profile.equals("mysql")) {
                System.setProperty("GENERATED_KEY_NAME", "GENERATED_KEY");
            }
        }
    }
}
