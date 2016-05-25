package com.gmail.shvetsova2015.inna.controllers;

import com.gmail.shvetsova2015.inna.entity.*;
import com.gmail.shvetsova2015.inna.services.MasterService;
import com.gmail.shvetsova2015.inna.services.OrderService;
import com.gmail.shvetsova2015.inna.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/")
public class MyController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/")
    public String index(Model model) throws ParseException {
        Time time = new Time();
        model.addAttribute("time", time.getTime());
        model.addAttribute("masters", masterService.activeList());
        model.addAttribute("services", productService.list());
        model.addAttribute("orders", sortedList());
        model.addAttribute("currentDate", curDate());
        model.addAttribute("dates", listStringDates());
        model.addAttribute("currentDateShort", curDateShort());
        model.addAttribute("chooseDate", curDateShort());
        return "index";
    }

    public static Date fromString(String sDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = formatter.parse(sDate);
        return date;
    }

    public static String curDate() {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
        Date currentDate = cal.getTime();
        return "Today is " + dateFormat.format(currentDate);
    }

    public static String curDateShort() {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
        Date currentDate = cal.getTime();
        return dateFormat.format(currentDate);
    }

    public static List<Date> listDates() throws ParseException {
        Calendar cal = Calendar.getInstance();
        List<Date> dates = new ArrayList<Date>();
        dates.add(cal.getTime());
        for (int i = 0; i < 6; i++) {
            cal.add(Calendar.DATE, 1);
            dates.add(cal.getTime());
        }
        return dates;
    }

    public static List<String> listStringDates() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
        List<String> listStringList = new ArrayList<>();
        try {
            for (Date d : listDates()) {
                listStringList.add(dateFormat.format(d));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listStringList;
    }

    public List<Order> sortedList() {
        List<Order> sorted = orderService.list();
        Collections.sort(sorted);
        return sorted;
    }
}