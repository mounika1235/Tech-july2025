package com.sat.tmf.movietkt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.ShowDao;
import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.Show;
import com.sat.tmf.movietkt.service.ShowService;

import java.util.List;

@Service
@Transactional
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowDao showDao;

    @Override
    public Show createShow(Show show) {
        showDao.save(show);
        return show;
    }

    @Override
    public Show updateShow(Show show) {
        showDao.saveOrUpdate(show);
        return show;
    }

    @Override
    public Show findById(Integer id) {
        return showDao.findById(id);
    }

    @Override
    public List<Show> findAll() {
        return showDao.findAll();
    }

    @Override
    public List<Show> findByScreen(Screen screen) {
        return showDao.findByScreen(screen);
    }

    @Override
//    public List<Show> findUpcomingShows(Movie movie) {
//        return showDao.findUpcomingShows(movie);
//    }
    
    public List<Show> findUpcomingShows(Movie movie) {
        return showDao.findUpcomingShowsWithTheaterAndScreen(movie);
    }
    @Override
    public void deleteShow(Integer id) {
        Show show = showDao.findById(id);
        if (show != null) {
            showDao.delete(show);
        }
    }

    @Override
    public void cleanOldShows() {
        showDao.deletePastShows();
    }
}
