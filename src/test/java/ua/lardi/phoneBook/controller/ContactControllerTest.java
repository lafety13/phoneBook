package ua.lardi.phoneBook.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.lardi.phoneBook.TestUtils;
import ua.lardi.phoneBook.dao.ContactDao;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.ContactServiceImpl;
import ua.lardi.phoneBook.service.UserService;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerTest {
    //contact fields
    private static final long CONTACT_ID = 1L;
    private static final String CONTACT_LAST_NAME = "testlastname";
    private static final String CONTACT_FIRST_NAME = "testfirstname";
    private static final String CONTACT_MIDDLE_NAME = "testmiddlename";
    private static final String CONTACT_MOBILE_PHONE = "+380983332222";
    private static final String CONTACT_HOME_PHONE = "testhomephone";
    private static final String CONTACT_ADDRESS = "testaddress";
    private static final String CONTACT_EMAIL = "testemail@domain.com";
    //user fields
    private static final String USER_LOGIN = "testlogin";
    private Contact contact;
    private Principal principal;
    private MockMvc mockMvc;
    private ContactServiceImpl service;
    private ContactDao repositoryMock;
    @Mock
    private ContactService contactServiceMock;
    @Mock
    private UserService userServiceMock;

    @Before
    public void setUp() throws Exception {
        repositoryMock = mock(ContactDao.class);
        service = new ContactServiceImpl();
        service.setContactDao(repositoryMock);

        ContactController contactController = new ContactController();
        contactController.setContactService(contactServiceMock);
        contactController.setUserService(userServiceMock);

        mockMvc = MockMvcBuilders.standaloneSetup(contactController)
                .setViewResolvers(TestUtils.viewResolver())
                .build();        contact = new Contact();

        contact.setId(CONTACT_ID);
        contact.setLastName(CONTACT_LAST_NAME);
        contact.setFirstName(CONTACT_FIRST_NAME);
        contact.setMiddleName(CONTACT_MIDDLE_NAME);
        contact.setMobilePhone(CONTACT_MOBILE_PHONE);
        contact.setHomePhone(CONTACT_HOME_PHONE);
        contact.setAddress(CONTACT_ADDRESS);
        contact.setEmail(CONTACT_EMAIL);

        principal = () -> USER_LOGIN;
    }

    @Test
    public void add_NewContactEntry_ShouldAddContactEntryAndRenderViewContactEntryView() throws Exception {
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                Contact toBeSaved = (Contact) args[0];
                return null;
            }
        }).when(contactServiceMock).save(Matchers.any(Contact.class));
        contactServiceMock.save(contact);
    }

    @Test
    public void get_NContactEntry_ShouldGetContactEntryAndRenderViewContactEntryView() throws Exception {
        when(contactServiceMock.findById(CONTACT_ID)).thenReturn(contact);
        mockMvc.perform(get("/contact/{id}", CONTACT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(),
                        Charset.forName("utf8")
                )))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.lastName", is(CONTACT_LAST_NAME)))
                .andExpect(jsonPath("$.firstName", is(CONTACT_FIRST_NAME)))
                .andExpect(jsonPath("$.middleName", is(CONTACT_MIDDLE_NAME)))
                .andExpect(jsonPath("$.mobilePhone", is(CONTACT_MOBILE_PHONE)))
                .andExpect(jsonPath("$.homePhone", is(CONTACT_HOME_PHONE)))
                .andExpect(jsonPath("$.address", is(CONTACT_ADDRESS)))
                .andExpect(jsonPath("$.email", is(CONTACT_EMAIL)));

        verify(contactServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(contactServiceMock);
    }

    @Test
    public void findAll_ShouldReturnListOfContactEntries() throws PersistenceException {
        List<Contact> models = new ArrayList<>();
        models.add(contact);

        when(repositoryMock.findAll()).thenReturn(models);
        List<Contact> actual = service.findAll();

        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(models));
    }
}