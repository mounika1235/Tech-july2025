package com.sat.tmf.movietkt.service;


import java.util.List;

import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.SeatTemplate;

public interface SeatTemplateService {
    SeatTemplate createTemplate(SeatTemplate template);
    SeatTemplate updateTemplate(SeatTemplate template);
    SeatTemplate findById(Integer id);
    List<SeatTemplate> findAll();
    List<SeatTemplate> findByScreen(Screen screen);
    void deleteTemplate(Integer id);
    
}

