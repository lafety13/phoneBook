package ua.lardi.phoneBook.dao.JacksonDao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("default")
public class ContactJacksonDao extends JacksonDaoSupport implements ContactDao {
    @Override
    public List<Contact> findAllContactsByUser(User user) {
        JsonPhoneBookModel jsonPhoneBookModel = new JsonPhoneBookModel();
        return jsonPhoneBookModel.getUsers().get(user.getLogin()).getContacts();
    }

    @Override
    public Contact save(Contact contact) {
        JsonPhoneBookModel jsonPhoneBookModel = readData();
        User user = jsonPhoneBookModel.getUsers().get(contact.getUser().getLogin());
        if (user.getContacts() == null) {
            user.setContacts(new ArrayList<>());
        }

        List<Contact> contactList = user.getContacts();
        long contactId = jsonPhoneBookModel.getContactCount() + 1;
        contact.setId(contactId);
        jsonPhoneBookModel.setContactCount(contactId);
        contactList.add(contact);
        writeData(jsonPhoneBookModel);

        return contact;
    }

    @Override
    public Contact findById(long id) {
        return null;
    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Contact> findAll() {
        return null;
    }
}
