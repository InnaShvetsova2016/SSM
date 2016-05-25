package com.gmail.shvetsova2015.inna.dao;

import com.gmail.shvetsova2015.inna.entity.Client;
import com.gmail.shvetsova2015.inna.entity.Order;

import java.util.List;

public interface OrderDAO {
    void add(Order order);
    void delete(Order order);
    void addPayment(Order order, double amount);
    Order find(String phone);
    List<Order> list();
    List<String> listClients();
}
