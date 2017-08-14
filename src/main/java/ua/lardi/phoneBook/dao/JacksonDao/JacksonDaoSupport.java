package ua.lardi.phoneBook.dao.JacksonDao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lardi.phoneBook.dao.PersistenceException;

import java.io.File;
import java.io.IOException;

public class JacksonDaoSupport {
    private static final Logger LOGGER = LogManager.getLogger(JacksonDaoSupport.class);
    private ObjectMapper mapper;
    private File file;

    public JsonPhoneBookModel readData() throws PersistenceException {
        try {
            return mapper.readValue(file, JsonPhoneBookModel.class);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new PersistenceException(e);
        }
    }

    public void writeData(JsonPhoneBookModel jsonPhoneBookModel) throws PersistenceException {
        file.delete();
        try {
            file.createNewFile();
            mapper.writeValue(file, jsonPhoneBookModel);
        } catch (IOException e) {
            LOGGER.error(e);
            throw new PersistenceException(e);
        }
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setFile(File file) {
        this.file = file;
    }
}
