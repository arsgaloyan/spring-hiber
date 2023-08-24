package hiber.dao;


import hiber.model.User;
import jakarta.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().persist(user.getCar());
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> showUser(String model, int series) {
        String HQL = "FROM User user LEFT JOIN FETCH user.car where user.car.model =: model and user.car.series =: series";
        TypedQuery<User> user = sessionFactory.getCurrentSession().createQuery(HQL, User.class)
                .setParameter("model", model)
                .setParameter("series", series);
        return user.getResultList();
    }
}
