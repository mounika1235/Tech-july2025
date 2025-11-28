
package com.sat.tmf.movietkt.service;

import java.util.List;

import com.sat.tmf.movietkt.entities.TemplateSeat;

public interface TemplateSeatService {
    List<TemplateSeat> findByTemplateId(Integer templateId);
    TemplateSeat saveSeat(TemplateSeat seat);
    void saveAllSeats(List<TemplateSeat> seats);
    void deleteSeatsByTemplate(Integer templateId);
}

