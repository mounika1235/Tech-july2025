package com.sat.tmf.movietkt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.RefundDao;
import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.Payment;
import com.sat.tmf.movietkt.entities.Refund;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.RefundService;

@Service
@Transactional
public class RefundServiceImpl implements RefundService {

    @Autowired
    private RefundDao refundDao;

    @Override
    public Refund initiateRefund(Payment payment, Booking booking, User user, BigDecimal amount) {
        Refund refund = new Refund();
        refund.setPayment(payment);
        refund.setBooking(booking);
        refund.setUser(user);
        refund.setAmount(amount);
        refund.setStatus("INITIATED");
        refund.setCreatedAt(LocalDateTime.now());
        refundDao.save(refund);
        return refund;
    }

    @Override
    public Refund processRefund(Integer refundId, String gatewayRefundTxnId) {
        Refund refund = refundDao.findById(refundId);
        if (refund != null) {
            refund.setRefundTxnId(gatewayRefundTxnId);
            refund.setStatus("SUCCESS");
            refund.setProcessedAt(LocalDateTime.now());
            refundDao.saveOrUpdate(refund);
        }
        return refund;
    }

    @Override
    public List<Refund> getRefundsForUser(User user) {
        return refundDao.findByUser(user);
    }

    @Override
    public List<Refund> getAllRefunds() {
        return refundDao.findAllRefunds();
    }
}
