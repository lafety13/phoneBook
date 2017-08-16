package ua.lardi.phoneBook.service;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.builders.ContactBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ContactServiceImplTest {
    private static final String CONTACT_LAST_NAME = "testlastname";
    private static final long CONTACT_ID = 1L;
    private static final String CONTACT_FIRST_NAME = "testfirstname";
    private static final String CONTACT_MIDDLE_NAME = "testmiddlename";
    private static final String CONTACT_MOBILE_PHONE = "+380983332222";
    private static final String CONTACT_HOME_PHONE = "+380445555555";
    private static final String CONTACT_ADDRESS = "testaddress";
    private static final String CONTACT_EMAIL = "testemail@domain.com";
    private Contact contact;
    private ContactServiceImpl service;
    private ContactDao repositoryMock;

    @Before
    public void setUp() {
        repositoryMock = mock(ContactDao.class);
        service = new ContactServiceImpl();
        service.setContactDao(repositoryMock);

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
    public void add_NewContactEntry_ShouldSaveContactEntry() throws PersistenceException {
        service.save(contact);

        ArgumentCaptor<Contact> toDoArgument = ArgumentCaptor.forClass(Contact.class);

        verify(repositoryMock, times(1)).save(toDoArgument.capture());
        verifyNoMoreInteractions(repositoryMock);

        Contact model = toDoArgument.getValue();

        assertTrue(model.getId().equals(0L));
        assertThat(model.getFirstName(), is(contact.getFirstName()));
        assertThat(model.getLastName(), is(contact.getLastName()));
        assertThat(model.getMiddleName(), is(contact.getMiddleName()));
        assertThat(model.getHomePhone(), is(contact.getHomePhone()));
        assertThat(model.getMobilePhone(), is(contact.getMobilePhone()));
        assertThat(model.getAddress(), is(contact.getAddress()));
        assertThat(model.getEmail(), is(contact.getEmail()));
    }

    @Test
    public void findAll_ShouldReturnListOfProductEntries() throws PersistenceException {
        List<Contact> models = new ArrayList<>();
        when(repositoryMock.findAll()).thenReturn(models);

        List<Contact> actual = service.findAll();

        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, Matchers.is(models));
    }

    @Test
    public void findById_ProductEntryFound_ShouldReturnFoundProductEntry() throws PersistenceException {
        when(repositoryMock.findById(CONTACT_ID)).thenReturn(contact);
        Contact actual = service.findById(CONTACT_ID);

        verify(repositoryMock, times(1)).findById(CONTACT_ID);
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, Matchers.is(contact));
    }

}