package com.gmail.shvetsova2015.inna.services;

import com.gmail.shvetsova2015.inna.dao.ClientDAO;
import com.gmail.shvetsova2015.inna.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientDAO clientDAO;

    @Transactional
    public void add(Client client) {
        clientDAO.add(client);
    }

    @Transactional
    public void delete(Client client) {
        clientDAO.delete(client);
    }

    @Transactional
    public void delete(int id) {
        clientDAO.delete(id);
    }

    @Transactional
    public void changePhone(int id, String phone) {
        clientDAO.changePhone(id, phone);
    }

    @Transactional (readOnly=true)
    public Client findSurname(String surname) {
        return clientDAO.findSurname(surname);
    }

    @Transactional (readOnly=true)
    public Client findPhone(String phone) {
        return clientDAO.findPhone(phone);
    }

    @Transactional (readOnly=true)
    public List<Client> list() {
        return clientDAO.list();
    }
}