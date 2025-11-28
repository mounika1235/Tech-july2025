package com.sat.tmf.movietkt.service;

import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.Payment;
import com.sat.tmf.movietkt.entities.User;

public interface PaymentService {
    Payment initiatePayment(Booking booking, User user, String gateway);
    void handlePaymentSuccess(String txnId, String gateway);
    void handlePaymentFailure(String txnId, String gateway);
}
