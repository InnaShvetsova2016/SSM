package com.gmail.shvetsova2015.inna.controllers;

import com.gmail.shvetsova2015.inna.entity.*;
import com.gmail.shvetsova2015.inna.services.ClientService;
import com.gmail.shvetsova2015.inna.services.MasterService;
import com.gmail.shvetsova2015.inna.services.OrderService;
import com.gmail.shvetsova2015.inna.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyControllerOrder {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MasterService masterService;

    @RequestMapping(value = "/add_order", method = RequestMethod.POST)
    public String addOrder(@RequestParam String surname, @RequestParam String name, @RequestParam String phone,
                           @RequestParam String product, @RequestParam String master, @RequestParam String date, @RequestParam String time,
                           Model model) throws ParseException {
        List<Client> clients = clientService.list();
        Client client = null;
        for (Client c : clients) {
            if (c.getSurname().equals(surname) && c.getName().equals(name) && c.getPhone().equals(phone)) {
                client = c;
            }
        }
        if (client == null) {
            client = new Client(surname, name, phone);
        }
        List<Product> products = productService.list();
        Product p = null;
        for (Product pr : products) {
            if (pr.getType().equals(product)) {
                p = pr;
            }
        }
        List<Master> masters = masterService.activeList();
        Master m = null;
        for (Master ms : masters) {
            if (ms.getName().equals(master)) {
                m = ms;
            }
        }

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date d = format.parse(date);
        String messageOrder = "";
        boolean flag = false;
        for (Order o : orderService.list()) {
            if (o.getMaster().getName().equals(m.getName()) && o.getTime().equals(time)) {
                flag = true;
            }
        }
        if (flag == true) {
            messageOrder = "ERROR! Time you want to choose is occupied!";
        } else {
            Order order = new Order(client, p, m, d, time);
            orderService.add(order);
            messageOrder = "Order was successfully added!";
        }

        Time time1 = new Time();
        model.addAttribute("messageOrder", messageOrder);
        model.addAttribute("time", time1.getTime());
        model.addAttribute("orders", sortedList());
        model.addAttribute("currentDate", MyController.curDate());
        model.addAttribute("masters", masterService.activeList());
        model.addAttribute("services", productService.list());
        model.addAttribute("dates", MyController.listStringDates());
        return "index";
    }

    @RequestMapping(value = "/choose_date", method = RequestMethod.POST)
    public String chooseDate(@RequestParam String chooseDate, Model model) {
        List<Order> ordersDate = new ArrayList<>();
        for (Order o : orderService.list()) {
            if (o.getSDate().equals(chooseDate)) {
                ordersDate.add(o);
            }
        }
        Collections.sort(ordersDate);
        Time time = new Time();
        model.addAttribute("time", time.getTime());
        model.addAttribute("chooseDate", chooseDate);
        model.addAttribute("orders", ordersDate);
        model.addAttribute("currentDate", MyController.curDate());
        model.addAttribute("masters", masterService.activeList());
        model.addAttribute("services", productService.list());
        model.addAttribute("dates", MyController.listStringDates());
        return "index";
    }

    @RequestMapping(value = "/add_amount", method = RequestMethod.POST)
    public String addAmount(@RequestParam double amount, @RequestParam int code, Model model) {

        for (Order o : orderService.list()) {
            if (o.getId() == code) {
                o.setAmount(amount);
                orderService.addPayment(o, amount);
            }
        }

        model.addAttribute("currentDate", MyController.curDate());
        Time time = new Time();
        model.addAttribute("time", time.getTime());
        model.addAttribute("masters", masterService.activeList());
        model.addAttribute("services", productService.list());
        model.addAttribute("orders", sortedList());
        model.addAttribute("currentDate", MyController.curDate());
        model.addAttribute("currentDateShort", MyController.curDateShort());
        model.addAttribute("dates", MyController.listStringDates());
        return "index";
    }

    @RequestMapping(value = "/delete_order", method = RequestMethod.POST)
    public String deleteOrder(@RequestParam int code, Model model) {

        for (Order o : orderService.list()) {
            if (o.getId() == code) {
                orderService.delete(o);
            }
        }

        model.addAttribute("currentDate", MyController.curDate());
        Time time = new Time();
        model.addAttribute("time", time.getTime());
        model.addAttribute("masters", masterService.activeList());
        model.addAttribute("services", productService.list());
        model.addAttribute("orders", sortedList());
        model.addAttribute("currentDate", MyController.curDate());
        model.addAttribute("currentDateShort", MyController.curDateShort());
        model.addAttribute("dates", MyController.listStringDates());
        return "index";
    }

    public List<Order> sortedList() {
        List<Order> sorted = orderService.list();
        Collections.sort(sorted);
        return sorted;
    }
}
