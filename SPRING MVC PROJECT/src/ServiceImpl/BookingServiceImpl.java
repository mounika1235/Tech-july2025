package com.sat.tmf.movietkt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.BookingDao;
import com.sat.tmf.movietkt.dao.TemplateSeatDao;
import com.sat.tmf.movietkt.entities.Booking;
import com.sat.tmf.movietkt.entities.BookingSeat;
import com.sat.tmf.movietkt.entities.Show;
import com.sat.tmf.movietkt.entities.TemplateSeat;
import com.sat.tmf.movietkt.entities.User;
import com.sat.tmf.movietkt.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired private BookingDao bookingDao;
    @Autowired private TemplateSeatDao seatDao;

//    @Override
//    public Booking holdSeats(User user, Show show, List<Integer> seatIds) {
//        // Create booking
//        Booking booking = new Booking();
//        booking.setUser(user);
//        booking.setShow(show);
//        booking.setBookingTime(LocalDateTime.now());
//        booking.setStatus("HOLD");
//        booking.setHoldExpiresAt(LocalDateTime.now().plusMinutes(10));
//        
//     // Initialize the seats list to avoid NullPointerException
//        booking.setSeats(new ArrayList<>());
//
//        List<TemplateSeat> selectedSeats = seatDao.findByTemplateId(show.getSeatTemplate().getId())
//                .stream()
//                .filter(seat -> seatIds.contains(seat.getId()))
//                .collect(Collectors.toList());
//
//        BigDecimal total = selectedSeats.stream()
//                .map(TemplateSeat::getPrice)
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        booking.setAmount(total);
//        bookingDao.save(booking);
//
//        // Create booking seat entries
//        for (TemplateSeat ts : selectedSeats) {
//            BookingSeat bs = new BookingSeat();
//            bs.setBooking(booking);
//            bs.setTemplateSeat(ts);
//            bs.setSeatCode(ts.getSeatCode());
//            bs.setPrice(ts.getPrice());
//            booking.getSeats().add(bs);
//        }
//
//        return booking;
//    }
        
 // In BookingServiceImpl.java - ensure the booking is properly saved
    @Override
    public Booking holdSeats(User user, Show show, List<Integer> seatIds) {
        // Create booking with HOLD status
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus("HOLD"); // Keep as HOLD until payment
        booking.setHoldExpiresAt(LocalDateTime.now().plusMinutes(10));
        
        // Initialize the seats list to avoid NullPointerException
        booking.setSeats(new ArrayList<>());

        List<TemplateSeat> selectedSeats = seatDao.findByTemplateId(show.getSeatTemplate().getId())
                .stream()
                .filter(seat -> seatIds.contains(seat.getId()))
                .collect(Collectors.toList());

        BigDecimal total = selectedSeats.stream()
                .map(TemplateSeat::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        booking.setAmount(total);
        
        // Save booking first to get an ID
        bookingDao.save(booking);

        // Create booking seat entries
        for (TemplateSeat ts : selectedSeats) {
            BookingSeat bs = new BookingSeat();
            bs.setBooking(booking);
            bs.setTemplateSeat(ts);
            bs.setSeatCode(ts.getSeatCode());
            bs.setPrice(ts.getPrice());
            booking.getSeats().add(bs);
        }

        // Update booking with seats
        bookingDao.saveOrUpdate(booking);
        
        return booking;
    }
    @Override
    public Booking confirmBooking(Integer bookingId) {
        Booking booking = bookingDao.findById(bookingId);
        booking.setStatus("CONFIRMED");
        bookingDao.saveOrUpdate(booking);
        return booking;
    }

    @Override
    public List<Booking> findByUser(User user) {
        return bookingDao.findByUser(user);
    }

    @Override
    public void cancelBooking(Integer bookingId) {
        Booking booking = bookingDao.findById(bookingId);
        booking.setStatus("CANCELLED");
        bookingDao.saveOrUpdate(booking);
    }

    @Override
    public Booking findById(Integer bookingId) {
        Booking booking = bookingDao.findById(bookingId);
        return booking;
    }
}
