package ua.lardi.phoneBook.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.lardi.phoneBook.model.Contact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ContactFormValidator implements Validator {
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String MOBILE_PATTERN = "^\\+?38\\(?0\\d{2}\\)?\\d{3}-?\\d{2}-?\\d{2}$";

    @Override
    public boolean supports(Class<?> aClass) {
        return Contact.class.equals(aClass);

    }

    @Override
    public void validate(Object o, Errors errors) {
        Contact contact = (Contact) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Required");
        if (contact.getLastName().length() < 4 || contact.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size.contactForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "Required");
        if (contact.getFirstName().length() < 4 || contact.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "middleName", "Required");
        if (contact.getMiddleName().length() < 4 || contact.getMiddleName().length() > 32) {
            errors.rejectValue("middleName", "Size.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobilePhone", "Required");
        if (!validate(MOBILE_PATTERN, contact.getMobilePhone())) {
            errors.rejectValue("mobilePhone", "Size.contactForm.PhoneNumber");
        }

        if (!"".equals(contact.getHomePhone()) && !validate(MOBILE_PATTERN, contact.getHomePhone())) {
            errors.rejectValue("homePhone", "Size.contactForm.PhoneNumber");
        }

        if (!"".equals(contact.getEmail()) && !validate(EMAIL_PATTERN, contact.getEmail())) {
            errors.rejectValue("email", "Size.contactForm.Email");
        }

        if (!"".equals(contact.getAddress()) && (contact.getAddress().length() < 3 || contact.getAddress().length() > 32)) {
            errors.rejectValue("address", "Size.contactForm.Adress");
        }
    }

    private boolean validate(String pattern, String value) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(value);
        return m.matches();
    }

}
