package edu.school21.services;

import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;

public class UsersServiceImpl {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    boolean authenticate(String login, String password) throws EntityNotFoundException {
        User user = usersRepository.findByLogin(login);
        if (user.getAuthenticationSuccessStatus()) {
            throw new AlreadyAuthenticatedException("Already authenticated");
        }
        if (user.getPassword().equals(password)) {
            user.setAuthenticationSuccessStatus();
            return true;
        }
        return false;
    }
}
