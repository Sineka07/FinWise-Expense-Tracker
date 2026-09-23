package com.finwise.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finwise.model.Income;
import com.finwise.model.User;

@Repository
@Transactional
public class IncomeDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void saveIncome(Income income) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(income);
    }
    
    public List<Income> getIncomesByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query<Income> query = session.createQuery(
            "FROM Income WHERE user = :user ORDER BY incomeDate DESC", Income.class);
        query.setParameter("user", user);
        return query.list();
    }
}