package com.gmail.shvetsova2015.inna.dao;

import com.gmail.shvetsova2015.inna.entity.Master;

import java.util.List;

public interface MasterDAO {
    void add(Master master);
    void delete(Master master);
    void delete(String phone);
    void delete(int id);
    void changeStatus(Master master);
    void changePhone(int id, String phone);
    Master findSurname(String surname);
    Master findPhone(String phone);
    List<Master> list();
    List<Master> activeList();
}

