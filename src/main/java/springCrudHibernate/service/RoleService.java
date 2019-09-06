package springCrudHibernate.service;

import org.springframework.stereotype.Service;
import springCrudHibernate.model.Role;
import springCrudHibernate.model.User;

import java.util.List;

public interface RoleService {

    boolean addRole(Role role);

    void deleteRoleById(Long id);

    void deleteAllRoles();

    boolean updateRole(Role role) ;

    List<Role> getAllRoles();

    Role getRoleById(Long id);

    Role getRoleByName(String name);
}
