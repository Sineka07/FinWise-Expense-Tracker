package com.finwise.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finwise.model.Expense;
import com.finwise.model.User;

@Repository
@Transactional
public class ExpenseDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public void saveExpense(Expense expense) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(expense);
    }
    
    public List<Expense> getExpensesByUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Query<Expense> query = session.createQuery(
            "FROM Expense WHERE user = :user ORDER BY expenseDate DESC", Expense.class);
        query.setParameter("user", user);
        return query.list();
    }
    
    public List<Expense> getExpensesByUserAndMonth(User user, String monthYear) {
        Session session = sessionFactory.getCurrentSession();
        Query<Expense> query = session.createQuery(
            "FROM Expense WHERE user = :user AND MONTH(expenseDate) = :month AND YEAR(expenseDate) = :year", Expense.class);
        query.setParameter("user", user);
        // Simplified - actual implementation would parse monthYear
        return query.list();
    }
    
    public void deleteExpense(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Expense expense = session.get(Expense.class, id);
        if (expense != null) {
            session.delete(expense);
        }
    }
}