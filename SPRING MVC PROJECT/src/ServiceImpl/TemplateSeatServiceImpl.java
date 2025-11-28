package com.sat.tmf.movietkt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.TemplateSeatDao;
import com.sat.tmf.movietkt.entities.TemplateSeat;
import com.sat.tmf.movietkt.service.TemplateSeatService;

import java.util.List;

@Service
@Transactional
public class TemplateSeatServiceImpl implements TemplateSeatService {

    @Autowired
    private TemplateSeatDao templateSeatDao;

    @Override
    public List<TemplateSeat> findByTemplateId(Integer templateId) {
        return templateSeatDao.findByTemplateId(templateId);
    }

    @Override
    public TemplateSeat saveSeat(TemplateSeat seat) {
        templateSeatDao.saveOrUpdate(seat);
        return seat;
    }

    @Override
    public void saveAllSeats(List<TemplateSeat> seats) {
        for (TemplateSeat s : seats) {
            templateSeatDao.saveOrUpdate(s);
        }
    }

    @Override
    public void deleteSeatsByTemplate(Integer templateId) {
        templateSeatDao.deleteByTemplateId(templateId);
    }
}
