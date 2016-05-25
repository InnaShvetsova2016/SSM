package com.gmail.shvetsova2015.inna.implement;

import com.gmail.shvetsova2015.inna.dao.ClientDAO;
import com.gmail.shvetsova2015.inna.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Client client) {
        entityManager.merge(client);
    }

    @Override
    public void delete(Client client) {
        entityManager.remove(client);
    }

    @Override
    public void delete(int id) {
        Client client;
        try {
            Query q = entityManager.createQuery("select c from Client c where c.id =:id", Client.class);
            q.setParameter("id", id);
            client = (Client) q.getSingleResult();
            entityManager.remove(client);
        } catch (NoResultException ex) {

        }
    }

    @Override
    public void changePhone(int id, String phone) {
        Client client;
        Query q = entityManager.createQuery("select c from Client c where c.id =:id", Client.class);
        q.setParameter("id", id);
        client = (Client) q.getSingleResult();
        client.setPhone(phone);
        entityManager.merge(client);
    }

    @Override
    public Client findSurname(String surname) {
        Client client = null;
        try {
            Query q = entityManager.createQuery("select c from Client c where c.surname =:surname", Client.class);
            q.setParameter("surname", surname);
            client = (Client) q.getSingleResult();
        } catch (NoResultException ex) {

        }
        return client;
    }

    @Override
    public Client findPhone(String phone) {
        Client client = null;
        try {
            Query q = entityManager.createQuery("select c from Client c where c.phone =:phone", Client.class);
            q.setParameter("phone", phone);
            client = (Client) q.getSingleResult();
        } catch (NoResultException ex) {

        }
        return client;
    }

    @Override
    public List<Client> list() {
        Query q = entityManager.createQuery("select c from Client c", Client.class);
        return (List<Client>) q.getResultList();
    }
}