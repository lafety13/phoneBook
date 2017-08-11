package ua.lardi.phoneBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;


    @Override
    public Contact save(Contact contact) {
        return contactDao.save(contact);
    }

    @Override
    public Contact findById(long id) {
        return contactDao.findById(id);
    }

    @Override
    public List<Contact> findAllContactsByUser(User user) {
        return contactDao.findAllContactsByUser(user);
    }

    @Override
    public void delete(long id) {
        contactDao.delete(id);
    }

    @Override
    public void update(Contact object) {

    }

    @Override
    public List<Contact> findAll() {
        return null;
    }
}
