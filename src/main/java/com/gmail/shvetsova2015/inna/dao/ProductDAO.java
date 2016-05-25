package com.gmail.shvetsova2015.inna.dao;

import com.gmail.shvetsova2015.inna.entity.Product;

import java.util.List;

public interface ProductDAO {
    void add(Product product);
    void delete(Product product);
    void change(Product product);
    Product find(String type);
    List<Product> list();
}
