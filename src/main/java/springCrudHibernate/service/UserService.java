package springCrudHibernate.service;


import org.springframework.stereotype.Service;
import springCrudHibernate.model.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    boolean validateUser(String login, String password);

    void deleteUserById(Long id);

    void deleteAllUsers();

    boolean updateUser(User user);

    List<User> getAllUsers();

    User getUserById(Long param);

    User getUserByLogin(String param);

    List<User> getUsersByRoles(String... params);

    List<User> getUsersByName(String param);
}
