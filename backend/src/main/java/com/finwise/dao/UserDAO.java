package com.finwise.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.finwise.model.User;

@Repository
@Transactional
public class UserDAO {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void saveUser(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }
    
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
    
    public User getUserByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
            "FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.getResultStream().findFirst().orElse(null);
    }
}