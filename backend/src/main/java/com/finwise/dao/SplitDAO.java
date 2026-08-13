// package com.finwise.dao;

// import com.finwise.model.SplitTransaction;
// import com.finwise.model.User;
// import org.hibernate.Session;
// import org.hibernate.SessionFactory;
// import org.hibernate.query.Query;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

// import java.util.List;

// @Repository
// @Transactional
// public class SplitDAO {
    
//     @Autowired
//     private SessionFactory sessionFactory;
    
//     public void saveSplit(SplitTransaction split) {
//         Session session = sessionFactory.getCurrentSession();
//         session.saveOrUpdate(split);
//     }
    
//     public List<SplitTransaction> getSplitsByUser(User user) {
//         Session session = sessionFactory.getCurrentSession();
//         Query<SplitTransaction> query = session.createQuery(
//             "FROM SplitTransaction WHERE paidBy = :user ORDER BY splitDate DESC", SplitTransaction.class);
//         query.setParameter("user", user);
//         return query.list();
//     }
// }