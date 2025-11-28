package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.Theater;

import java.util.List;

@Repository
public class ScreenDao extends GenericDao<Screen, Integer> {

    public ScreenDao() {
        super(Screen.class);
    }

    /**
     * Find all screens for a specific theater.
     */
    public List<Screen> findByTheater(Theater theater) {
        Session session = getSession();
        Query<Screen> query = session.createQuery(
                "from Screen s where s.theater.id = :tid order by s.name", Screen.class);
        query.setParameter("tid", theater.getId());
        return query.list();
    }

    /**
     * Search screen by name (case-insensitive).
     */
    public List<Screen> searchByName(String name) {
        Session session = getSession();
        Query<Screen> query = session.createQuery(
                "from Screen where lower(name) like :n", Screen.class);
        query.setParameter("n", "%" + name.toLowerCase() + "%");
        return query.list();
    }
}
