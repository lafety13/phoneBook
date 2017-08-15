package ua.lardi.phoneBook.validator;

import org.junit.Before;
import org.springframework.validation.Errors;
import ua.lardi.phoneBook.model.User;

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

    private Errors error;
    private User user;
    private User badUser;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setName(USER_NAME);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);

        badUser = new User();
        badUser.setId(1L);
        badUser.setLogin(BAD_USER_LOGIN_SHORT);
        badUser.setName(BAD_USER_NAME_SHORT);
        badUser.setPassword(BAD_USER_PASSWORD_SHORT);
    }

//    @Test
//    public void successfulVerifyUser() throws Exception {
//        error = new BeanPropertyBindingResult(user, "user");
//        userFormValidator.validate(user, error);
//       // assertFalse(error.hasErrors());
//    }
//
//    @Test
//    public void badUser() throws Exception {
//        error = new BeanPropertyBindingResult(badUser, "badUser");
//        userFormValidator.validate(badUser, error);
//        assertTrue(error.hasErrors());
//    }

}