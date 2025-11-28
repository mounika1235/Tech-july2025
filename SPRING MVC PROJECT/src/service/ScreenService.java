
package com.sat.tmf.movietkt.service;


import java.util.List;

import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.Theater;

public interface ScreenService {
    Screen createScreen(Screen screen);
    Screen updateScreen(Screen screen);
    Screen findById(Integer id);
    List<Screen> findAll();
    List<Screen> findByTheater(Theater theater);
    void deleteScreen(Integer id);
}
