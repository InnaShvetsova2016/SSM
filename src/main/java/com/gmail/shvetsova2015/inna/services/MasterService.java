package com.gmail.shvetsova2015.inna.services;

import com.gmail.shvetsova2015.inna.dao.MasterDAO;
import com.gmail.shvetsova2015.inna.entity.Master;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MasterService {
    @Autowired
    private MasterDAO masterDAO;

    @Transactional
    public void add(Master master) {
        masterDAO.add(master);
    }

    @Transactional
    public void delete(Master master) {
        masterDAO.delete(master);
    }

    @Transactional
    public void delete(String phone) {
        masterDAO.delete(phone);
    }

    @Transactional
    public void delete(int id) {
        masterDAO.delete(id);
    }

    @Transactional
    public void changeStatus(Master master) {
        masterDAO.changeStatus(master);
    }

    @Transactional
    public void changePhone(int id, String phone) {
        masterDAO.changePhone(id, phone);
    }

    @Transactional(readOnly = true)
    public Master findSurname(String surname) {
        return masterDAO.findSurname(surname);
    }

    @Transactional(readOnly = true)
    public Master findPhone(String phone) {
        return masterDAO.findPhone(phone);
    }

    @Transactional(readOnly = true)
    public List<Master> list() {
        return masterDAO.list();
    }


    @Transactional(readOnly = true)
    public List<Master> activeList() {
        return masterDAO.activeList();
    }
}
