package ua.lardi.phoneBook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.lardi.phoneBook.dao.JacksonDao.JsonPhoneBookModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 * @author Vadim Kozak
 */
@Configuration
@Profile("default")
public class JacksonDaoConfig {

    @Bean
    public File jsonFile() throws IOException {
        //reading path from config file
        String path = System.getProperty("jsonpath");
        File file;
        //if no path in config, creating file in resources
        if (path==null) {
            file = new File("src/main/resources/phonebook.json");
        } else {
            if (!path.endsWith("/")) {
                path = path + "/";
            }
            file = new File(path + "phonebook.json");
        }
        if (file.createNewFile()) {
            JsonPhoneBookModel data = new JsonPhoneBookModel();
            data.setUsers(new HashMap<>());
            objectMapper().writeValue(file, data);
        }
        return file;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
