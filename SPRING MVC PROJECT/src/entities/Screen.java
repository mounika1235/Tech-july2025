package com.sat.tmf.movietkt.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "screens")
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SeatTemplate> seatTemplates;
    
    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Theater getTheater() { return theater; }
    public void setTheater(Theater theater) { this.theater = theater; }

    public List<SeatTemplate> getSeatTemplates() { return seatTemplates; }
    public void setSeatTemplates(List<SeatTemplate> seatTemplates) { this.seatTemplates = seatTemplates; }
}

