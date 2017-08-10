package ua.lardi.phoneBook.dao.JacksonDao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;

public class JacksonDaoSupport {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private File file;

    public JsonPhoneBookModel readData() {
        try {
            return mapper.readValue(file, JsonPhoneBookModel.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
            ////////////////////////////
        }
    }

    public void writeData(JsonPhoneBookModel jsonPhoneBookModel) {
        file.delete();
        try {
            file.createNewFile();
            mapper.writeValue(file, jsonPhoneBookModel);
        } catch (IOException e) {

        }
    }
}
