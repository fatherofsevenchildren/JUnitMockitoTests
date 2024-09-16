package edu.school21.repositories;

import edu.school21.exceptions.EntityNotFoundException;
import edu.school21.models.User;

public interface UsersRepository {
    User findByLogin(String login) throws EntityNotFoundException;
    void update(User user) throws EntityNotFoundException;
}
