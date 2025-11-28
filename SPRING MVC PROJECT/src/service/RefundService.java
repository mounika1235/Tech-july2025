package com.sat.tmf.movietkt.service;


import java.math.BigDecimal;
import java.util.List;

import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.Payment;
import com.sat.tmf.movietkt.entities.Refund;
import com.sat.tmf.movietkt.entities.User;

public interface RefundService {
    Refund initiateRefund(Payment payment, Booking booking, User user, BigDecimal amount);
    Refund processRefund(Integer refundId, String gatewayRefundTxnId);
    List<Refund> getRefundsForUser(User user);
    List<Refund> getAllRefunds();
}
