package ua.lardi.phoneBook.model;

import org.junit.Before;
import org.junit.Test;
import ua.lardi.phoneBook.model.builders.ContactBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactTest {
    private Contact contact;

    @Before
    public void setUp() throws Exception {
        contact = new ContactBuilder()
                .setFirstName("name")
                .setLastName("lastname")
                .setMiddleName("middlename")
                .setMobilePhone("38(095)2341515")
                .setHomePhone("38(095)2341515")
                .setAddress("addres")
                .setEmail("email.gmail.com")
                .build();
    }

    @Test
    public void build_AllInformationGiven_ShouldCreateNewObjectAndSetAllInformation() {
        assertTrue(contact.getId() == 0);
        assertEquals("name", contact.getFirstName());
        assertEquals("lastname", contact.getLastName());
        assertEquals("middlename", contact.getMiddleName());
        assertEquals("38(095)2341515", contact.getMobilePhone());
        assertEquals("38(095)2341515", contact.getHomePhone());
        assertEquals("addres", contact.getAddress());
        assertEquals("email.gmail.com", contact.getEmail());
    }

}