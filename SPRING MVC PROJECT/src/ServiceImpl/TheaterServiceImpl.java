package com.sat.tmf.movietkt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.TheaterDao;
import com.sat.tmf.movietkt.entities.Theater;
import com.sat.tmf.movietkt.service.TheaterService;

import java.util.List;

@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    private TheaterDao theaterDao;

    @Override
    public Theater createTheater(Theater theater) {
        theaterDao.save(theater);
        return theater;
    }

    @Override
    public Theater updateTheater(Theater theater) {
        theaterDao.saveOrUpdate(theater);
        return theater;
    }

    @Override
    public Theater findById(Integer id) {
        return theaterDao.findById(id);
    }

    @Override
    public List<Theater> findAll() {
        return theaterDao.findAll();
    }

    @Override
    public List<Theater> findByCity(String city) {
        return theaterDao.findByCity(city);
    }

    @Override
    public void deleteTheater(Integer id) {
        Theater t = theaterDao.findById(id);
        if (t != null) {
            theaterDao.delete(t);
        }
    }
}
