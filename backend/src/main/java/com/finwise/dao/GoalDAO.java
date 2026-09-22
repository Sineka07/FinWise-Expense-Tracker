package com.finwise.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finwise.model.Goal;
import com.finwise.model.User;

@Repository
@Transactional
public class GoalDAO {
    
	@Autowired
	private SessionFactory sessionFactory;
    
	public void saveGoal(Goal goal) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(goal);
	}
    
	public List<Goal> getGoalsByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query<Goal> query = session.createQuery(
			"FROM Goal WHERE user = :user ORDER BY targetDate ASC", Goal.class);
		query.setParameter("user", user);
		return query.list();
	}
}