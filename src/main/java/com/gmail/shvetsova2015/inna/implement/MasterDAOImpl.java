package com.gmail.shvetsova2015.inna.implement;

import com.gmail.shvetsova2015.inna.dao.MasterDAO;
import com.gmail.shvetsova2015.inna.entity.Master;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MasterDAOImpl implements MasterDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Master master) {
        entityManager.merge(master);
    }

    @Override
    public void delete(Master master) {
        entityManager.remove(master);
    }

    @Override
    public void delete(String phone) {
        Master master;
        try {
            Query q = entityManager.createQuery("select m from Master m where m.phone =:phone", Master.class);
            q.setParameter("phone", phone);
            master = (Master) q.getSingleResult();
            entityManager.remove(master);
        } catch (NoResultException ex) {

        }
    }

    @Override
    public void delete(int id) {
        Master master;
        try {
            Query q = entityManager.createQuery("select m from Master m where m.id =:id", Master.class);
            q.setParameter("id", id);
            master = (Master) q.getSingleResult();
            entityManager.remove(master);
        } catch (NoResultException ex) {

        }
    }

    @Override
    public void changeStatus(Master master) {
        if ((master.getStatus()).equals("active")) {
            master.setStatus("non-active");
        } else {
            master.setStatus("active");
        }
        entityManager.merge(master);
    }

    @Override
    public Master findSurname(String surname) {
        Master master = null;
        try {
            Query q = entityManager.createQuery("select m from Master m where m.surname =:surname", Master.class);
            q.setParameter("surname", surname);
            master = (Master) q.getSingleResult();
        } catch (NoResultException ex) {

        }
        return master;
    }

    @Override
    public Master findPhone(String phone) {
        Master master = null;
        try {
            Query q = entityManager.createQuery("select m from Master m where m.phone =:phone", Master.class);
            q.setParameter("phone", phone);
            master = (Master) q.getSingleResult();
        } catch (NoResultException ex) {

        }
        return master;
    }

    @Override
    public void changePhone(int id, String phone) {
        Master master;
        Query q = entityManager.createQuery("select m from Master m where m.id =:id", Master.class);
        q.setParameter("id", id);
        master = (Master) q.getSingleResult();
        master.setPhone(phone);
        entityManager.merge(master);
    }

    @Override
    public List<Master> list() {
        Query q = entityManager.createQuery("select m from Master m", Master.class);
        return (List<Master>) q.getResultList();
    }

    @Override
    public List<Master> activeList() {
        Query q = entityManager.createQuery("select m from Master m where m.status=:status", Master.class);
        q.setParameter("status", "active");
        return (List<Master>) q.getResultList();
    }
}
