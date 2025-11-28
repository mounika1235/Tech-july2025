package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Theater;

import java.util.List;

@Repository
public class TheaterDao extends GenericDao<Theater, Integer> {

    public TheaterDao() {
        super(Theater.class);
    }

    /**
     * Find all theaters in a specific city.
     */
    public List<Theater> findByCity(String city) {
        Session session = getSession();
        Query<Theater> query = session.createQuery("from Theater where city = :city", Theater.class);
        query.setParameter("city", city);
        return query.list();
    }

    /**
     * Search by partial name (case-insensitive).
     */
    public List<Theater> searchByName(String keyword) {
        Session session = getSession();
        Query<Theater> query = session.createQuery("from Theater where lower(name) like :kw", Theater.class);
        query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
        return query.list();
    }
}
