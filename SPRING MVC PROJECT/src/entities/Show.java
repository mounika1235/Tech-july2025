package com.sat.tmf.movietkt.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "movie_id", nullable = false)
//    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)  // Changed from LAZY to EAGER
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_template_id")
    private SeatTemplate seatTemplate;

    @Column(nullable = false)
    private LocalDateTime showTime;

    private Integer totalSeats;

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public Screen getScreen() { return screen; }
    public void setScreen(Screen screen) { this.screen = screen; }

    public SeatTemplate getSeatTemplate() { return seatTemplate; }
    public void setSeatTemplate(SeatTemplate seatTemplate) { this.seatTemplate = seatTemplate; }

    public LocalDateTime getShowTime() { return showTime; }
    public void setShowTime(LocalDateTime showTime) { this.showTime = showTime; }

    public Integer getTotalSeats() { return totalSeats; }
    public void setTotalSeats(Integer totalSeats) { this.totalSeats = totalSeats; }
}

