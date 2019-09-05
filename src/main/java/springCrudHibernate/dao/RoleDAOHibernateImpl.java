package springCrudHibernate.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import springCrudHibernate.model.Role;

@Repository
public class RoleDAOHibernateImpl extends AbstractHibernateDAO<Role> {
//    private static RoleDAOHibernateImpl instance;

//    public static RoleDAOHibernateImpl getInstance() {
//        if (instance == null)
//            instance = new RoleDAOHibernateImpl();
//        return instance;
//    }

    private RoleDAOHibernateImpl() {
        super.setModelClass(Role.class);
    }
}
