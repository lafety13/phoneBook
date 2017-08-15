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
import org.springframework.web.context.WebApplicationContext;
import ua.lardi.phoneBook.TestUtils;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
public class AuthControllerTest {
    //user fields
    private static final String USER_LOGIN = "testlogin";
    private static final String USER_NAME = "testname";
    private static final String USER_PASSWORD = "testpassword";
    private User user;
    private MockMvc mockMvc;
    @Mock
    private UserService userServiceMock;
    @Mock
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(userServiceMock);

        AuthController authController = new AuthController();
        authController.setUserService(userServiceMock);

        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setViewResolvers(TestUtils.viewResolver())
                .build();

        user = new User();
        user.setName(USER_NAME);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD);
    }

    @Test
    public void showRegistrationForm_ShouldRenderRegistrationView() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"))
                .andExpect(forwardedUrl("/WEB-INF/views/registration.jsp"));
    }

}