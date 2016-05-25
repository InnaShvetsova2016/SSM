package com.gmail.shvetsova2015.inna.implement;

import com.gmail.shvetsova2015.inna.dao.OrderDAO;
import com.gmail.shvetsova2015.inna.entity.Client;
import com.gmail.shvetsova2015.inna.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Order order) {
        entityManager.merge(order);
    }

    @Override
    public void delete(Order order) {
        entityManager.remove(entityManager.merge(order));
        entityManager.close();
    }

    @Override
    public void addPayment(Order order, double amount) {
        order.setAmount(amount);
        order.setStatus("paid");
        entityManager.merge(order);
        Client c = order.getClient();
        c.setLastVisitDate(order.getDate());
        entityManager.merge(c);
    }

    @Override
    public Order find(String phone) {
        Order order = null;
        try {
            Query q = entityManager.createQuery("select o from Order o where o.phone =:phone", Order.class);
            q.setParameter("phone", phone);
            order = (Order) q.getSingleResult();
        } catch (NoResultException ex) {

        }
        return order;
    }

    @Override
    public List<Order> list() {
        Query q = entityManager.createQuery("select o from Order o", Order.class);
        for (Order o:(List<Order>) q.getResultList()) {
            System.out.println(o.toString());
        }
        return (List<Order>) q.getResultList();
    }


    @Override
    public List<String> listClients() {
        List<String> sn = new ArrayList<>();
        Query q = entityManager.createQuery("select o from Order o", Order.class);
        List<Order> o = q.getResultList();;
        for (Order i: o) {
            sn.add(i.getClientSN());
            System.out.println(i.getClientSN());
        }
        return sn;
    }
}
