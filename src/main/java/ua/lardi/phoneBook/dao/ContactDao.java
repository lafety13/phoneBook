package ua.lardi.phoneBook.dao;

import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.util.List;

public interface ContactDao extends GenericDao<Contact> {
    List<Contact> findAllContactsByUser(User user);
}
