package com.sat.tmf.movietkt.service;


import java.util.List;

import com.sat.tmf.movietkt.entities.Theater;

public interface TheaterService {
    Theater createTheater(Theater theater);
    Theater updateTheater(Theater theater);
    Theater findById(Integer id);
    List<Theater> findAll();
    List<Theater> findByCity(String city);
    void deleteTheater(Integer id);
}
