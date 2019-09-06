package springCrudHibernate.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import springCrudHibernate.model.Role;

@Repository
public class RoleDAOHibernateImpl extends AbstractHibernateDAO<Role> {
    private RoleDAOHibernateImpl(SessionFactory sessionFactory, Role entity) {
        super(sessionFactory, entity);
    }
}
