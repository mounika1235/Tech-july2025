
package com.sat.tmf.movietkt.service;


import java.util.List;

import com.sat.tmf.movietkt.entities.Movie;
import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.Show;

public interface ShowService {
    Show createShow(Show show);
    Show updateShow(Show show);
    Show findById(Integer id);
    List<Show> findAll();
    List<Show> findByScreen(Screen screen);
    List<Show> findUpcomingShows(Movie movie);
    void deleteShow(Integer id);
    void cleanOldShows();
}

