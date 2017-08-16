package ua.lardi.phoneBook.model;

import org.junit.Before;
import org.junit.Test;
import ua.lardi.phoneBook.model.builders.UserBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTest {
    private static final String USER_LOGIN = "testlogin";
    private static final String USER_NAME = "testname";
    private static final String USER_PASSWORD = "testpassword";
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new UserBuilder()
                .setLogin(USER_LOGIN)
                .setName(USER_NAME)
                .setPassword(USER_PASSWORD)
                .build();
    }

    @Test
    public void build_AllInformationGiven_ShouldCreateNewObjectAndSetAllInformation() {
        assertTrue(user.getId() == 0);
        assertEquals("testlogin", user.getLogin());
        assertEquals("testname", user.getName());
        assertEquals("testpassword", user.getPassword());
    }
}