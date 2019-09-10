package springCrudHibernate.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springCrudHibernate.dao.DAO;
import springCrudHibernate.model.Role;
import springCrudHibernate.model.User;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final DAO<User> dao;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(DAO<User> dao, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        if (getUserByLogin(user.getLogin()) != null)
            return false;
        user.setRoles(user.getRoles().stream().map(role -> roleService.getRoleByName(role.getName())).collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.add(user);
        return true;
    }

    @Transactional
    public boolean validateUser(String login, String password) {
        User user = getUserByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        dao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllUsers() {
        dao.deleteAll();
    }

    @Transactional
    @Override
    public boolean updateUser(User user) {
        User old = getUserById(user.getId());
        if (old.getLogin().equals(user.getLogin())) {
            if (!old.getPassword().equals(user.getPassword()) && !user.getPassword().isEmpty())
                old.setPassword(passwordEncoder.encode(user.getPassword()));
            dao.update(old);
            return true;
        } else if (getUserByLogin(user.getLogin()) == null) {
            dao.update(user);
            return true;
        } else
            return false;
    }

    @Transactional
    @Override
    public List<User> getAllUsers() {
        return dao.getAll();
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return dao.getUniqueByParam(id, "id");
    }

    @Transactional
    @Override
    public User getUserByLogin(String login) {
        return dao.getUniqueByParam(login, "login");
    }

    @Transactional
    @Override
    public List<User> getUsersByRoles(String... rolesNames) {
        Collection<User> users = roleService.getRoleByName(rolesNames[0]).getUsers();

        for (int i = 1; i < rolesNames.length; i++) {
            Iterator<User> itr = users.iterator();
            while (itr.hasNext()) {
                if (!itr.next().getRoles().contains(new Role(rolesNames[i])))
                    itr.remove();
            }
        }
        return new ArrayList<>(users);
    }

    @Transactional
    @Override
    public List<User> getUsersByName(String name) {
        return dao.getListByParam(name, "name");
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = getUserByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("user is not exist");
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList()));
    }
}
