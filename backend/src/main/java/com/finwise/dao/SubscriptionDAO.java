package com.finwise.dao;

import com.finwise.model.Subscription;
import com.finwise.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SubscriptionDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void saveSubscription(Subscription subscription) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(subscription);
    }
    
    public List<Subscription> getSubscriptionsByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query<Subscription> query = session.createQuery(
            "FROM Subscription WHERE user = :user ORDER BY renewalDate ASC", Subscription.class);
        query.setParameter("user", user);
        return query.list();
    }
}