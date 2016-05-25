package com.gmail.shvetsova2015.inna.services;

import com.gmail.shvetsova2015.inna.dao.OrderDAO;
import com.gmail.shvetsova2015.inna.entity.Client;
import com.gmail.shvetsova2015.inna.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public void add(Order order) {
        orderDAO.add(order);
    }

    @Transactional
    public void delete(Order order) {
        orderDAO.delete(order);
    }

    @Transactional
    public void addPayment(Order order, double amount) {orderDAO.addPayment(order, amount);}

    @Transactional (readOnly=true)
    public Order find(String phone) {return orderDAO.find(phone);}

    @Transactional (readOnly=true)
    public List<Order> list() {
        return orderDAO.list();
    }

    @Transactional (readOnly=true)
    public List<String> listClients() {
        return orderDAO.listClients();
    }
}
