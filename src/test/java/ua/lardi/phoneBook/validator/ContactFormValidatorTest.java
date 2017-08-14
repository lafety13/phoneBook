package ua.lardi.phoneBook.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ua.lardi.phoneBook.model.Contact;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class ContactFormValidatorTest {
    //contact fields
    private static final String CONTACT_LAST_NAME = "testlastname";
    private static final String CONTACT_FIRST_NAME = "testfirstname";
    private static final String CONTACT_MIDDLE_NAME = "testmiddlename";
    private static final String CONTACT_MOBILE_PHONE = "+380983332222";
    private static final String CONTACT_HOME_PHONE = "+380445555555";
    private static final String CONTACT_ADDRESS = "testaddress";
    private static final String CONTACT_EMAIL = "testemail@domain.com";
    //bad contact
    private static final String BAD_CONTACT_LAST_NAME = "ame";
    private static final String BAD_CONTACT_FIRST_NAME = "ame";
    private static final String BAD_CONTACT_MIDDLE_NAME = "ame";
    private static final String BAD_CONTACT_MOBILE_PHONE = "+3809832222";
    private static final String BAD_CONTACT_EMAIL = "testemail@domain";
    @InjectMocks
    private final ContactFormValidator contactFormValidator = new ContactFormValidator();
    private Errors error;
    private Contact contact;
    private Contact badContact;

    @Before
    public void setUp() {
        contact = new Contact();
        contact.setId(1L);
        contact.setLastName(CONTACT_LAST_NAME);
        contact.setFirstName(CONTACT_FIRST_NAME);
        contact.setMiddleName(CONTACT_MIDDLE_NAME);
        contact.setMobilePhone(CONTACT_MOBILE_PHONE);
        contact.setHomePhone(CONTACT_HOME_PHONE);
        contact.setAddress(CONTACT_ADDRESS);
        contact.setEmail(CONTACT_EMAIL);

        badContact = new Contact();
        badContact.setId(1L);
        badContact.setLastName(BAD_CONTACT_LAST_NAME);
        badContact.setFirstName(BAD_CONTACT_FIRST_NAME);
        badContact.setMiddleName(BAD_CONTACT_MIDDLE_NAME);
        badContact.setMobilePhone(BAD_CONTACT_MOBILE_PHONE);
        badContact.setHomePhone(CONTACT_HOME_PHONE);
        badContact.setAddress(CONTACT_ADDRESS);
        badContact.setEmail(BAD_CONTACT_EMAIL);
    }

    @Test
    public void successfulVerifyUser() throws Exception {
        error = new BeanPropertyBindingResult(contact, "contact");
        contactFormValidator.validate(contact, error);
        assertFalse(error.hasErrors());
    }

    @Test
    public void badUser() throws Exception {
        error = new BeanPropertyBindingResult(badContact, "badContact");
        contactFormValidator.validate(badContact, error);
        assertTrue(error.hasErrors());
    }
}