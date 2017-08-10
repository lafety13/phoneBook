package ua.lardi.phoneBook.dao.JacksonDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ua.lardi.phoneBook.WebApplication;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebApplication.class)
@WebAppConfiguration
@ActiveProfiles("json")
public class ContactJacksonDaoTest {
    //contact fields
    private static final String CONTACT_LAST_NAME = "testlastname";
    private static final String CONTACT_FIRST_NAME = "testfirstname";
    private static final String CONTACT_MIDDLE_NAME = "testmiddlename";
    private static final String CONTACT_MOBILE_PHONE = "testmobilephone";
    private static final String CONTACT_HOME_PHONE = "testhomephone";
    private static final String CONTACT_ADDRESS = "testaddress";
    private static final String CONTACT_EMAIL = "testemail";
    private static final String CONTACT_CHANGED_FIRST_NAME = "changedname";
    //user fields
    private static final String USER_LOGIN = "testlogin";
    private static final String USER_NAME = "testname";
    private static final String USER_PASSWORD = "testpassword";

    private User user;
    private Contact contact;

    @Autowired
    private ContactDao contactDao;


    @Before
    public void setUp() {

    }

    @Test
    public void getAllContactsByUserTest() {

        List<Contact> contactsToDelete = new ArrayList<>();
        //creating test contacts and puting them to DB
        for (int i = 0; i < 4; i++) {
            Contact contact = new Contact();
            contact.setFirstName(CONTACT_FIRST_NAME + i);
            contact.setLastName(CONTACT_LAST_NAME + i);
            contact.setMiddleName(CONTACT_MIDDLE_NAME + i);
            contact.setMobilePhone(CONTACT_MOBILE_PHONE + i);
            contact.setHomePhone(CONTACT_HOME_PHONE + i);
            contact.setAddress(CONTACT_ADDRESS + i);
            contact.setEmail(CONTACT_EMAIL + i);
            contact.setUser(user);
            contactsToDelete.add(contactDao.save(contact));
        }

        //retrieving all contacts by user
        List<Contact> contacts = contactDao.findAllContactsByUser(user);
        assertEquals(4, contacts.size());

        //deleting test contacts
        for (Contact contact : contactsToDelete) {
            contactDao.delete(contact.getId());
        }

    }
}