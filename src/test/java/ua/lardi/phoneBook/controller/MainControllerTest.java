package ua.lardi.phoneBook.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.lardi.phoneBook.TestUtils;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class MainControllerTest {
    private static final String CONTACT_LAST_NAME = "testlastname";
    private static final String CONTACT_FIRST_NAME = "testfirstname";
    private static final String CONTACT_MIDDLE_NAME = "testmiddlename";
    private static final String CONTACT_MOBILE_PHONE = "+380983332222";
    private static final String CONTACT_HOME_PHONE = "testhomephone";
    private static final String CONTACT_ADDRESS = "testaddress";
    private static final String CONTACT_EMAIL = "testemail@domain.com";
    //user fields
    private static final String USER_LOGIN = "testlogin";
    private static final String USER_NAME = "testname";
    private static final String USER_PASSWORD = "testpassword";
    private List<Contact> contacts;
    private User user;
    private Principal principal;
    private MockMvc mockMvc;
    @Mock
    private UserService userServiceMock;
    @Mock
    private ContactService contactServiceMock;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(userServiceMock, contactServiceMock);
        MainController mainController = new MainController();
        mainController.setUserService(userServiceMock);
        mainController.setContactService(contactServiceMock);

        mockMvc = MockMvcBuilders.standaloneSetup(mainController)
                .setViewResolvers(TestUtils.viewResolver())
                .build();

        contacts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Contact contact = new Contact();
            contact.setLastName(CONTACT_LAST_NAME + i);
            contact.setFirstName(CONTACT_FIRST_NAME + i);
            contact.setMiddleName(CONTACT_MIDDLE_NAME + i);
            contact.setMobilePhone(CONTACT_MOBILE_PHONE + i);
            contact.setHomePhone(CONTACT_HOME_PHONE + i);
            contact.setAddress(CONTACT_ADDRESS + i);
            contact.setEmail(i + CONTACT_EMAIL);
            contacts.add(contact);
        }
        user = new User();
        user.setName(USER_NAME);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);

        principal = () -> USER_LOGIN;
    }

    @Test
    public void shouldRenderViewHomePage() throws Exception {
        when(userServiceMock.findUserByLogin(USER_LOGIN)).thenReturn(user);
        when(contactServiceMock.findAllContactsByUser(user)).thenReturn(contacts);

        mockMvc.perform(get("/home").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(forwardedUrl("/WEB-INF/views/home.jsp"))
                .andExpect(model().attribute("username", equalTo(USER_NAME)))
                .andExpect(model().attribute("contacts", hasSize(4)))
                .andExpect(model().attribute("contactForm", is(new Contact())));

        verify(userServiceMock, times(1)).findUserByLogin(USER_LOGIN);
        verify(contactServiceMock, times(1)).findAllContactsByUser(user);
    }
}