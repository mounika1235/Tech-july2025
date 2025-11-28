package com.sat.tmf.movietkt.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "template_seats")
public class TemplateSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rowLabel;
    private Integer col;
    private String seatCode;

    @Column(length = 50)
    private String seatType; // REGULAR, VIP, DISABLED

    private BigDecimal price;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_template_id", nullable = false)
    private SeatTemplate seatTemplate;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getRowLabel() { return rowLabel; }
    public void setRowLabel(String rowLabel) { this.rowLabel = rowLabel; }

    public Integer getCol() { return col; }
    public void setCol(Integer col) { this.col = col; }

    public String getSeatCode() { return seatCode; }
    public void setSeatCode(String seatCode) { this.seatCode = seatCode; }

    public String getSeatType() { return seatType; }
    public void setSeatType(String seatType) { this.seatType = seatType; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public SeatTemplate getSeatTemplate() { return seatTemplate; }
    public void setSeatTemplate(SeatTemplate seatTemplate) { this.seatTemplate = seatTemplate; }
}

