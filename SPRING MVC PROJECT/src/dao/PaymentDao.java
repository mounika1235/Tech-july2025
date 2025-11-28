package com.sat.tmf.movietkt.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sat.tmf.movietkt.entities.Payment;

import jakarta.persistence.Query;

@Repository
public class PaymentDao extends GenericDao<Payment, Integer> {
    public PaymentDao() {
        super(Payment.class);
    }

    public Payment findByTxnId(String txnId) {
        return (Payment) getSession()
                .createQuery("from Payment where txnId = :txn")
                .setParameter("txn", txnId)
                .uniqueResult();
    }
    
    /**
     * Find Payment by associated Booking ID.
     */
    public Payment findByBookingId(Integer bookingId) {
        Session session = getSession();
        Query query = session.createQuery(
                "from Payment where booking.id = :bid", Payment.class);
        query.setParameter("bid", bookingId);
        return ((org.hibernate.query.Query<Payment>) query).uniqueResult();
    }
}
