package com.gmail.shvetsova2015.inna.controllers;

import com.gmail.shvetsova2015.inna.entity.Product;
import com.gmail.shvetsova2015.inna.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class MyControllerProduct {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public String services(Model model) {
        model.addAttribute("products", productService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "products";
    }

    @RequestMapping(value = "/add_product", method = RequestMethod.POST)
    public String addProduct(@RequestParam String type, @RequestParam double price, @RequestParam int timeLimit, Model model) {
        Product product = new Product(type, price, timeLimit);
        productService.add(product);
        model.addAttribute("products", productService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "products";
    }

    @RequestMapping(value = "/product_find_type", method = RequestMethod.POST)
    public String findProductType(@RequestParam String type, Model model) {
        String message2 = "Success!";
        List<Product> list_find = new ArrayList<>();
        for (Product p : productService.list()) {
            if (p.getType().equals(type)) {
                list_find.add(p);
            }
        }
        if (list_find.size() == 0) {
            message2 = "No such service in our database!";
        }
        model.addAttribute("message2", message2);
        model.addAttribute("products_find_type", list_find);
        model.addAttribute("currentDate", MyController.curDate());
        return "products_find_type";
    }
}
