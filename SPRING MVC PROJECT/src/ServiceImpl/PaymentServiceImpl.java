package com.sat.tmf.movietkt.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.PaymentDao;
import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.Payment;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.BookingService;
import com.sat.tmf.movietkt.service.EmailService;
import com.sat.tmf.movietkt.service.PaymentService;
import com.sat.tmf.movietkt.service.RefundService;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired 
    private PaymentDao paymentDao;
    
    @Autowired 
    private BookingService bookingService;
    
    @Autowired(required = false) // Make it optional
    private EmailService emailService;
    
    @Autowired(required = false) // Make it optional
    private RefundService refundService;

    @Override
    public Payment initiatePayment(Booking booking, User user, String gateway) {
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setUser(user);
        payment.setAmount(booking.getAmount());
        payment.setStatus("INITIATED");
        payment.setGateway(gateway);
        payment.setTxnId(UUID.randomUUID().toString()); // replace with Razorpay/Stripe txn id
        payment.setCreatedAt(LocalDateTime.now());

        paymentDao.save(payment);
        return payment;
    }

    @Override
    public void handlePaymentSuccess(String txnId, String gateway) {
        Payment payment = paymentDao.findByTxnId(txnId);
        if (payment != null && "INITIATED".equals(payment.getStatus())) {
            payment.setStatus("SUCCESS");
            paymentDao.saveOrUpdate(payment);

            // Confirm booking
            Booking booking = payment.getBooking();
            bookingService.confirmBooking(booking.getId());

            // Send confirmation email (if email service is available)
            try {
                if (emailService != null) {
                    emailService.sendBookingConfirmation(payment.getUser(), booking);
                }
            } catch (Exception e) {
                System.err.println("Email sending failed, but booking is confirmed: " + e.getMessage());
                // Continue without email - booking is still confirmed
            }
        }
    }

    @Override
    public void handlePaymentFailure(String txnId, String gateway) {
        Payment payment = paymentDao.findByTxnId(txnId);
        if (payment != null) {
            payment.setStatus("FAILED");
            paymentDao.saveOrUpdate(payment);

            // Send failure email (if email service is available)
            try {
                if (emailService != null) {
                    emailService.sendPaymentFailure(payment.getUser(), payment.getBooking());
                }
            } catch (Exception e) {
                System.err.println("Email sending failed: " + e.getMessage());
                // Continue without email
            }
        }
    }
    

    public void handleBookingCancellation(Booking booking) {
        Payment payment = paymentDao.findByBookingId(booking.getId());
        if (payment != null && "SUCCESS".equals(payment.getStatus())) {
            if (refundService != null) {
                refundService.initiateRefund(payment, booking, booking.getUser(), payment.getAmount());
            }
            // Optionally call Razorpay refund API and then mark as SUCCESS
        }
    }
}
