package com.sat.tmf.movietkt.service;

import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.User;

public interface EmailService {
    void sendBookingConfirmation(User user, Booking booking);
    void sendPaymentFailure(User user, Booking booking);
}
