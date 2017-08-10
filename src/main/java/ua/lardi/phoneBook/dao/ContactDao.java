package ua.goit.phoneBook.dao;

import ua.goit.phoneBook.model.Contact;
import ua.goit.phoneBook.model.User;

import java.util.List;

public interface ContactDao extends GenericDao<Contact> {
    List<Contact> findAllContactsByUser(User user);
}
