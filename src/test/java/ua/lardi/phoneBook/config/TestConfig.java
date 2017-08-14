package ua.lardi.phoneBook.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ua.lardi.phoneBook.service.ContactService;
import ua.lardi.phoneBook.service.UserService;
import ua.lardi.phoneBook.validator.ContactFormValidator;
import ua.lardi.phoneBook.validator.UserFormValidator;

@Configuration
@Profile("test")
@ComponentScan("ua.lardi.phoneBook.controller")
public class TestConfig {

    @Bean
    public UserService userService() {
        return Mockito.mock(UserService.class);
    }

    @Bean
    public ContactService contactService() {
        return Mockito.mock(ContactService.class);
    }

    @Bean
    public UserFormValidator userFormValidator() {
        return Mockito.mock(UserFormValidator.class);
    }
    @Bean
    public ContactFormValidator contactFormValidator() {
        return Mockito.mock(ContactFormValidator.class);
    }
}