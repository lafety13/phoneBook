package ua.lardi.phoneBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactDao contactDao;

    @Override
    public void save(Contact contact) throws PersistenceException {
        if (contact.getId() == 0) {
            contactDao.save(contact);
        } else {
            contactDao.update(contact);
        }
    }

    @Override
    public Contact findById(long id) throws PersistenceException {
        return contactDao.findById(id);
    }

    @Override
    public List<Contact> findAllContactsByUser(User user) throws PersistenceException {
        return contactDao.findAllContactsByUser(user);
    }

    @Override
    public void delete(long id) throws PersistenceException {
        contactDao.delete(id);
    }

    @Override
    public void update(Contact contact) throws PersistenceException {
        contactDao.update(contact);
    }

    @Override
    public List<Contact> findAll() throws PersistenceException {
        return contactDao.findAll();
    }

    @Autowired
    public void setContactDao(ContactDao contactDao) {
        this.contactDao = contactDao;
    }
}
