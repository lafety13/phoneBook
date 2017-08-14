package ua.lardi.phoneBook.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.Errors;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class UserFormValidatorTest {
    //user fields
    private static final String USER_LOGIN = "testlogin";
    private static final String USER_NAME = "testname";
    private static final String USER_PASSWORD = "testpassword";
    //bad user fields
    private static final String BAD_USER_LOGIN_SHORT = "lo";
    private static final String BAD_USER_LOGIN_RUSSIAN = "логин";
    private static final String BAD_USER_NAME_SHORT = "name";
    private static final String BAD_USER_PASSWORD_SHORT = "word";

    @InjectMocks
    private final UserFormValidator userFormValidator = new UserFormValidator();

    private Errors error;
    private User user;
    private User badUser;
    @Mock
    private UserDao userDao;

    @Before
    public void setUp() {
        user = new User();
        user.setName(USER_NAME);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);

        badUser = new User();
        badUser.setLogin(BAD_USER_LOGIN_SHORT);
        badUser.setName(BAD_USER_NAME_SHORT);
        badUser.setPassword(BAD_USER_PASSWORD_SHORT);
    }

    @Test
    public void successfulVerifyUser() throws Exception {
        userFormValidator.validate(user, error);
        assertFalse(error.hasErrors());
    }

    @Test
    public void badUser() throws Exception {
        userFormValidator.validate(badUser, error);
        assertTrue(error.hasErrors());
    }

}