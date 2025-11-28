
package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.User;

import java.util.List;
@Repository
public class UserDao extends GenericDao<User, Integer> {

    public UserDao() {
        super(User.class);
    }

    public User findByUsername(String username) {
        Session session = getSession();
        Query<User> query = session.createQuery("from User where username = :uname", User.class);
        query.setParameter("uname", username);
        return query.uniqueResult();
    }

  
    public User findByEmail(String email) {
        Session session = getSession();
        Query<User> query = session.createQuery("from User where email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }

    /**
     * Return all users.
     */
    public List<User> findAllUsers() {
        Session session = getSession();
        return session.createQuery("from User", User.class).list();
    }

  
    public User authenticate(String username, String password) {
     
        return findByUsername(username);
    }
}
