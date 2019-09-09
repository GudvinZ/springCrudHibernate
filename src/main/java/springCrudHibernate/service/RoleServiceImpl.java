package springCrudHibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springCrudHibernate.dao.DAO;
import springCrudHibernate.model.Role;


import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final DAO<Role> dao;

    private RoleServiceImpl(DAO<Role> dao) {
        this.dao = dao;
    }

    @Transactional
    @Override
    public boolean addRole(Role role) {
        if (getRoleByName(role.getName()) == null) {
            dao.add(role);
            return true;
        } else
            return false;
    }

    @Transactional
    @Override
    public void deleteRoleById(Long id) {
        dao.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllRoles() {
        dao.deleteAll();
    }

    @Transactional
    @Override
    public boolean updateRole(Role role) {
        dao.update(role);
        return true;
    }

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return dao.getAll();
    }

    @Transactional
    @Override
    public Role getRoleById(Long id) {
        return dao.getUniqueByParam(id, "id");
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return dao.getUniqueByParam(name, "name");
    }
}
