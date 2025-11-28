
package com.sat.tmf.movietkt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Refund;
import com.sat.tmf.movietkt.entities.User;

@Repository
public class RefundDao extends GenericDao<Refund, Integer> {

    public RefundDao() {
        super(Refund.class);
    }

    public List<Refund> findByUser(User user) {
        Session session = getSession();
        Query<Refund> query = session.createQuery(
                "from Refund where user.id = :uid order by createdAt desc", Refund.class);
        query.setParameter("uid", user.getId());
        return query.list();
    }

    public List<Refund> findAllRefunds() {
        return getSession().createQuery("from Refund order by createdAt desc", Refund.class).list();
    }
}

