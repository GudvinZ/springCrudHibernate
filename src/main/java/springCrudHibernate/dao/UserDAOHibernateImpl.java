package springCrudHibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import springCrudHibernate.model.User;

@Repository
public class UserDAOHibernateImpl extends AbstractHibernateDAO<User> {
    UserDAOHibernateImpl(SessionFactory sessionFactory, User entity) {
        super(sessionFactory, entity);
    }

    @Override
    public void add(User user) {
        Session session = super.getSessionFactory().getCurrentSession();
        session.merge(user);
    }

    @Override
    public void update(User user) {
        Session session = super.getSessionFactory().getCurrentSession();
        session.merge(user);
    }
}
