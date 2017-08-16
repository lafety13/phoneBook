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
import ua.lardi.phoneBook.model.builders.ContactBuilder;

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
        contact = new ContactBuilder()
                .setId(1L)
                .setFirstName(CONTACT_FIRST_NAME)
                .setLastName(CONTACT_LAST_NAME)
                .setMiddleName(CONTACT_MIDDLE_NAME)
                .setMobilePhone(CONTACT_MOBILE_PHONE)
                .setHomePhone(CONTACT_HOME_PHONE)
                .setAddress(CONTACT_ADDRESS)
                .setEmail(CONTACT_EMAIL)
                .build();

        badContact = new ContactBuilder()
                .setId(1L)
                .setFirstName(BAD_CONTACT_FIRST_NAME)
                .setLastName(BAD_CONTACT_LAST_NAME)
                .setMiddleName(BAD_CONTACT_MIDDLE_NAME)
                .setMobilePhone(BAD_CONTACT_MOBILE_PHONE)
                .setHomePhone(BAD_CONTACT_MOBILE_PHONE)
                .setAddress(CONTACT_ADDRESS)
                .setEmail(BAD_CONTACT_EMAIL)
                .build();
    }

    @Test
    public void successfulVerifyContact() throws Exception {
        error = new BeanPropertyBindingResult(contact, "contact");
        contactFormValidator.validate(contact, error);
        assertFalse(error.hasErrors());
    }

    @Test
    public void badContact() throws Exception {
        error = new BeanPropertyBindingResult(badContact, "badContact");
        contactFormValidator.validate(badContact, error);
        assertTrue(error.hasErrors());
    }
}