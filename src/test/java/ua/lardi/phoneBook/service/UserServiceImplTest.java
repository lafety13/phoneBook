package ua.lardi.phoneBook.service;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import ua.lardi.phoneBook.dao.PersistenceException;
import ua.lardi.phoneBook.dao.UserDao;
import ua.lardi.phoneBook.model.User;
import ua.lardi.phoneBook.model.builders.UserBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private static final String USER_LOGIN = "testlogin";
    private static final String USER_NAME = "testname";
    private static final String USER_PASSWORD = "testpassword";
    private User user;
    private UserServiceImpl service;
    private UserDao repositoryMock;

    @Before
    public void setUp() {
        repositoryMock = mock(UserDao.class);
        service = new UserServiceImpl();
        ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
        service.setShaPasswordEncoder(shaPasswordEncoder);
        service.setUserDao(repositoryMock);

        user = new UserBuilder()
                .setLogin(USER_LOGIN)
                .setName(USER_NAME)
                .setPassword(USER_PASSWORD)
                .build();
    }

    @Test
    public void add_NewUserEntry_ShouldSaveUserEntry() throws PersistenceException {
        service.save(user);

        ArgumentCaptor<User> toDoArgument = ArgumentCaptor.forClass(User.class);

        verify(repositoryMock, times(1)).save(toDoArgument.capture());
        verifyNoMoreInteractions(repositoryMock);

        User model = toDoArgument.getValue();

        assertThat(model.getLogin(), is(user.getLogin()));
        assertThat(model.getName(), is(user.getName()));
        assertThat(model.getPassword(), is(user.getPassword()));
    }

    @Test
    public void findAll_ShouldReturnListOfUserEntries() throws PersistenceException {
        List<User> models = new ArrayList<>();
        when(repositoryMock.findAll()).thenReturn(models);

        List<User> actual = service.findAll();

        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, Matchers.is(models));
    }

    @Test
    public void findById_UserEntryFound_ShouldReturnFoundUserEntry() throws PersistenceException {
        when(repositoryMock.findById(1L)).thenReturn(user);
        User actual = service.findById(1L);

        verify(repositoryMock, times(1)).findById(1L);
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, Matchers.is(user));
    }

}