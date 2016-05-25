package com.gmail.shvetsova2015.inna.controllers;

import com.gmail.shvetsova2015.inna.entity.Order;
import com.gmail.shvetsova2015.inna.entity.Salary;
import com.gmail.shvetsova2015.inna.services.MasterService;
import com.gmail.shvetsova2015.inna.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/")
public class MyControllerSalary {

    @Autowired
    private MasterService masterService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/salary")
    public String salary(Model model) {
        List<Salary> salaryList = new ArrayList<>();
        for (int i = 0; i < masterService.list().size(); i++) {
            salaryList.add(new Salary());
            salaryList.get(i).setM(masterService.list().get(i));
        }
        int count = 0;
        double profit = 0.00;
        for (int i = 0; i < masterService.list().size(); i++) {
            for (Order o : orderService.list()) {
                if (o.getMasterName().equals(masterService.list().get(i).getName())) {
                    count += 1;
                    profit += o.getAmount();
                }
            }
            salaryList.get(i).setCount(count);
            salaryList.get(i).setProfit(profit);
            count = 0;
            profit = 0.00;
        }
        int totalCount = 0;
        double totalProfit = 0.00;
        for (int i = 0; i < salaryList.size(); i++) {
            totalCount += salaryList.get(i).getCount();
            totalProfit += salaryList.get(i).getProfit();
        }
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalProfit", totalProfit);
        model.addAttribute("salary", salaryList);
        model.addAttribute("masters", masterService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "salary";
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    public String salaryPeriod(@RequestParam String fromDate, @RequestParam String toDate, Model model) throws ParseException {
        List<Salary> salaryListPeriod = new ArrayList<>();
        for (int i = 0; i < masterService.list().size(); i++) {
            salaryListPeriod.add(new Salary());
            salaryListPeriod.get(i).setM(masterService.list().get(i));
        }
        int count = 0;
        double profit = 0.00;
        Date fromD = MyController.fromString(fromDate);
        Date toD = MyController.fromString(toDate);
        for (int i = 0; i < masterService.list().size(); i++) {
            for (Order o : orderService.list()) {
                if (o.getDate().getTime() >= fromD.getTime() && o.getDate().getTime() <= toD.getTime()) {
                    if (o.getMasterName().equals(masterService.list().get(i).getName())) {
                        count += 1;
                        profit += o.getAmount();
                    }
                }
            }
            salaryListPeriod.get(i).setCount(count);
            salaryListPeriod.get(i).setProfit(profit);
            count = 0;
            profit = 0.00;
        }
        int totalCountPeriod = 0;
        double totalProfitPeriod = 0.00;
        for (int i = 0; i < salaryListPeriod.size(); i++) {
            totalCountPeriod += salaryListPeriod.get(i).getCount();
            totalProfitPeriod += salaryListPeriod.get(i).getProfit();
        }
        model.addAttribute("totalCount", totalCountPeriod);
        model.addAttribute("totalProfit", totalProfitPeriod);
        model.addAttribute("salary", salaryListPeriod);
        model.addAttribute("masters", masterService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "salary";
    }
}

