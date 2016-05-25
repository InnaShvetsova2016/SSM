package com.gmail.shvetsova2015.inna.dao;

import com.gmail.shvetsova2015.inna.entity.Client;

import java.util.List;

public interface ClientDAO {
    void add(Client client);
    void delete(Client client);
    void delete(int id);
    void changePhone(int id, String phone);
    Client findSurname(String surname);
    Client findPhone(String phone);
    List<Client> list();
}
