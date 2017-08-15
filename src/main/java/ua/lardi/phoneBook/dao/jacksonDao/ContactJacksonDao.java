package ua.lardi.phoneBook.dao.jacksonDao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Profile("default")
public class ContactJacksonDao extends AbstractJacksonDao implements ContactDao {
    @Override
    public List<Contact> findAllContactsByUser(User user) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        return data.getUsers().get(user.getLogin()).getContacts();
    }

    @Override
    public Contact save(Contact contact) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        //getting current user
        User user = data.getUsers().get(contact.getUser().getLogin());
        //check if user already has contacts, if not setting empty List
        if (user.getContacts() == null) {
            user.setContacts(new ArrayList<>());
        }
        List<Contact> userContacts = user.getContacts();
        long contactId = data.getContactCount() + 1;
        contact.setId(contactId);
        data.setContactCount(contactId);
        userContacts.add(contact);
        writeData(data);
        return contact;
    }

    @Override
    public Contact findById(long id) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        for (User user : data.getUsers().values()) {
            List<Contact> userContacts = user.getContacts();
            for (Contact contact : userContacts) {
                if (contact.getId() == id) {
                    contact.setUser(user);
                    return contact;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Contact contact) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        List<Contact> userContacts = data.getUsers().get(contact.getUser().getLogin()).getContacts();
        for (int i = 0; i < userContacts.size(); i++) {
            if (userContacts.get(i).getId() == contact.getId()) {
                userContacts.set(i, contact);
                writeData(data);
                break;
            }
        }
    }

    @Override
    public void delete(long id) throws PersistenceException {
        JsonPhoneBookModel data = readData();
        for (Map.Entry<String, User> entry : data.getUsers().entrySet()) {
            List<Contact> contacts = entry.getValue().getContacts();
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getId() == id) {
                    contacts.remove(i);
                    writeData(data);
                    return;
                }
            }
        }
    }

    @Override
    public List<Contact> findAll() throws PersistenceException {
        JsonPhoneBookModel data = readData();
        List<Contact> result = new ArrayList<>();
        for (User user : data.getUsers().values()) {
            result.addAll(user.getContacts());
        }
        return result;
    }
}
