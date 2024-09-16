package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    void setUp() throws EntityNotFoundException {
        User newUser = new User(1L, "new_user@test.com", "password");
        User authenticatedUser = new User(2L, "old_user@test.com", "password");
        authenticatedUser.setAuthenticationSuccessStatus();
        lenient().when(usersRepository.findByLogin("new_user@test.com")).thenReturn(newUser);
        lenient().when(usersRepository.findByLogin("old_user@test.com")).thenReturn(authenticatedUser);
    }

    @Test
    void authenticateTrue() throws EntityNotFoundException {
        assertTrue(usersService.authenticate("new_user@test.com", "password"));
    }

    @Test
    void authenticateFalse() throws EntityNotFoundException {
        assertFalse(usersService.authenticate("new_user@test.com", "wrong_test"));
    }

    @Test
    void authenticateException() throws EntityNotFoundException {
        Assertions.assertThrows(AlreadyAuthenticatedException.class, () -> {
            usersService.authenticate("old_user@test.com", "password");
        });
    }
}
