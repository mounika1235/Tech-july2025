package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.SeatTemplate;

import java.util.List;

@Repository
public class SeatTemplateDao extends GenericDao<SeatTemplate, Integer> {

    public SeatTemplateDao() {
        super(SeatTemplate.class);
    }

    /**
     * Find templates for a specific screen.
     */
    public List<SeatTemplate> findByScreen(Screen screen) {
        Session session = getSession();
        Query<SeatTemplate> query = session.createQuery(
                "from SeatTemplate where screen.id = :sid", SeatTemplate.class);
        query.setParameter("sid", screen.getId());
        return query.list();
    }

    /**
     * Find template by name for a given screen.
     */
    public SeatTemplate findByNameAndScreen(String name, Screen screen) {
        Session session = getSession();
        Query<SeatTemplate> query = session.createQuery(
                "from SeatTemplate where name = :n and screen.id = :sid", SeatTemplate.class);
        query.setParameter("n", name);
        query.setParameter("sid", screen.getId());
        return query.uniqueResult();
    }
}

