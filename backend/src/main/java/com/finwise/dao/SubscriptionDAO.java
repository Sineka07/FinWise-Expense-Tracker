package com.finwise.dao;

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
    
    public void saveSubscription(Object subscription) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(subscription);
    }
    
    @SuppressWarnings("rawtypes")
    public List getSubscriptionsByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(
            "FROM Subscription WHERE user = :user ORDER BY renewalDate ASC",
            Object.class);
        query.setParameter("user", user);
        return query.list();
    }
}