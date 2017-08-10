package ua.lardi.phoneBook.service;

import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;

import java.util.List;

public interface ContactService extends GenericService<Contact> {
    List<Contact> findAllContactsByUser(User user);
}
