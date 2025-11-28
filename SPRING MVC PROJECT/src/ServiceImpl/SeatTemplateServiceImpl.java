package com.sat.tmf.movietkt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sat.tmf.movietkt.dao.SeatTemplateDao;
import com.sat.tmf.movietkt.entities.Screen;
import com.sat.tmf.movietkt.entities.SeatTemplate;
import com.sat.tmf.movietkt.service.SeatTemplateService;

import java.util.List;

@Service
@Transactional
public class SeatTemplateServiceImpl implements SeatTemplateService {

    @Autowired
    private SeatTemplateDao seatTemplateDao;

    @Override
    public SeatTemplate createTemplate(SeatTemplate template) {
        seatTemplateDao.save(template);
        return template;
    }

    @Override
    public SeatTemplate updateTemplate(SeatTemplate template) {
        seatTemplateDao.saveOrUpdate(template);
        return template;
    }

    @Override
    public SeatTemplate findById(Integer id) {
        return seatTemplateDao.findById(id);
    }

    @Override
    public List<SeatTemplate> findAll() {
        return seatTemplateDao.findAll();
    }

    @Override
    public List<SeatTemplate> findByScreen(Screen screen) {
        return seatTemplateDao.findByScreen(screen);
    }

    @Override
    public void deleteTemplate(Integer id) {
        SeatTemplate t = seatTemplateDao.findById(id);
        if (t != null) {
            seatTemplateDao.delete(t);
        }
    }
}
