package com.sat.tmf.movietkt.service;

import java.util.List;

import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.Show;
import com.sat.tmf.movietkt.entities.User;

public interface BookingService {
    Booking holdSeats(User user, Show show, List<Integer> seatIds);
    Booking confirmBooking(Integer bookingId);
    List<Booking> findByUser(User user);
    void cancelBooking(Integer bookingId);
	Booking findById(Integer bookingId);
}
