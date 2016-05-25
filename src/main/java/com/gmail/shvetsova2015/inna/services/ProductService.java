package com.gmail.shvetsova2015.inna.services;

import com.gmail.shvetsova2015.inna.dao.ProductDAO;
import com.gmail.shvetsova2015.inna.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public void add(Product product) {
        productDAO.add(product);
    }

    @Transactional
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Transactional
    public void change(Product product) {
        productDAO.change(product);
    }

    @Transactional(readOnly = true)
    public Product find(String type) {
        return productDAO.find(type);
    }

    @Transactional(readOnly = true)
    public List<Product> list() {
        return productDAO.list();
    }
}