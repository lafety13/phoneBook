package ua.lardi.phoneBook.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import ua.lardi.phoneBook.model.Contact;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.UserService;
import ua.lardi.phoneBook.validator.ContactFormValidator;

import java.nio.charset.Charset;
import java.security.Principal;

import static org.hamcrest.Matchers.is;
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
    private static final String USER_NAME = "testname";
    private static final String USER_PASSWORD = "testpassword";

    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    private Contact contact;
    private User user;
    private Principal principal;

    private MockMvc mockMvc;

    @Mock
    private ContactService contactServiceMock;
    @Mock
    private UserService userServiceMock;
    @Mock
    private ContactFormValidator contactFormValidatorMock;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(userServiceMock, contactServiceMock, contactFormValidatorMock);

        ContactController contactController = new ContactController();
        contactController.setContactService(contactServiceMock);
        contactController.setUserService(userServiceMock);
        contactController.setContactFormValidator(contactFormValidatorMock);

        mockMvc = MockMvcBuilders.standaloneSetup(contactController)
                .setViewResolvers(viewResolver())
                .build();        contact = new Contact();

        contact.setId(CONTACT_ID);
        contact.setLastName(CONTACT_LAST_NAME);
        contact.setFirstName(CONTACT_FIRST_NAME);
        contact.setMiddleName(CONTACT_MIDDLE_NAME);
        contact.setMobilePhone(CONTACT_MOBILE_PHONE);
        contact.setHomePhone(CONTACT_HOME_PHONE);
        contact.setAddress(CONTACT_ADDRESS);
        contact.setEmail(CONTACT_EMAIL);

        user = new User();
        user.setName(USER_NAME);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);
        principal = () -> USER_LOGIN;
    }

    @Test
    public void add_NewTodoEntry_ShouldAddTodoEntryAndRenderViewTodoEntryView() throws Exception {




    }

    @Test
    public void getContactTest() throws Exception {
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
    public void deleteById_ProductEntryFound_ShouldDeleteTodoEntryAndRenderProductListView() throws Exception {
        mockMvc.perform(get("/delete/{id}", CONTACT_ID))
                .andExpect(status().isMovedTemporarily())
                .andExpect(view().name("redirect:/home"));

        verify(contactServiceMock, times(1)).delete(CONTACT_ID);
        verifyNoMoreInteractions(contactServiceMock);
    }

    private ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

        return viewResolver;
    }
}