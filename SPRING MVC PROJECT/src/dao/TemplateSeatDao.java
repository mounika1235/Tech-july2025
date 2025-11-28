package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.TemplateSeat;

import java.util.List;

@Repository
public class TemplateSeatDao extends GenericDao<TemplateSeat, Integer> {

    public TemplateSeatDao() {
        super(TemplateSeat.class);
    }

    /**
     * Get all seats for a given template.
     */
    public List<TemplateSeat> findByTemplateId(Integer templateId) {
        Session session = getSession();
        Query<TemplateSeat> query = session.createQuery(
                "from TemplateSeat where seatTemplate.id = :tid order by rowLabel, col", TemplateSeat.class);
        query.setParameter("tid", templateId);
        return query.list();
    }

    /**
     * Delete all seats for a template (useful for reset).
     */
    public void deleteByTemplateId(Integer templateId) {
        Session session = getSession();
        session.createQuery("delete from TemplateSeat where seatTemplate.id = :tid")
               .setParameter("tid", templateId)
               .executeUpdate();
    }
}

