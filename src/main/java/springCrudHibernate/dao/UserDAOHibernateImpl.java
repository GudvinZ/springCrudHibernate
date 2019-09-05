package springCrudHibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import springCrudHibernate.model.User;

@Repository
public class UserDAOHibernateImpl extends AbstractHibernateDAO<User> {
//    private static UserDAOHibernateImpl instance;

//    public static UserDAOHibernateImpl getInstance() {
//        if (instance == null)
//            instance = new UserDAOHibernateImpl();
//        return instance;
//    }


    private UserDAOHibernateImpl() {
        super.setModelClass(User.class);
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
