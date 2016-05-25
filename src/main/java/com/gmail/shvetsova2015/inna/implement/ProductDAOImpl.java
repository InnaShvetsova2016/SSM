package com.gmail.shvetsova2015.inna.implement;

import com.gmail.shvetsova2015.inna.dao.ProductDAO;
import com.gmail.shvetsova2015.inna.entity.Product;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void delete(Product product) {
        entityManager.remove(product);
    }

    @Override
    public void change(Product product) {

    }

    @Override
    public Product find(String type) {
        Product product = null;
        try {
            Query q = entityManager.createQuery("select p from Product p where p.type =:type", Product.class);
            q.setParameter("type", type);
            product = (Product) q.getSingleResult();
        } catch (NoResultException ex) {

        }
        return product;
    }

    @Override
    public List<Product> list() {
        Query q = entityManager.createQuery("select p from Product p", Product.class);
        return (List<Product>) q.getResultList();
    }
}