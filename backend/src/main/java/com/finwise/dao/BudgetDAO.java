package com.finwise.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finwise.model.Budget;
import com.finwise.model.User;

@Repository
@Transactional
public class BudgetDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void saveBudget(Budget budget) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(budget);
    }
    
    public List<Budget> getBudgetsByUserAndMonth(User user, String monthYear) {
        Session session = sessionFactory.getCurrentSession();
        Query<Budget> query = session.createQuery(
            "FROM Budget WHERE user = :user AND monthYear = :monthYear", Budget.class);
        query.setParameter("user", user);
        query.setParameter("monthYear", monthYear);
        return query.list();
    }
}