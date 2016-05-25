package com.gmail.shvetsova2015.inna.controllers;

import com.gmail.shvetsova2015.inna.entity.Master;
import com.gmail.shvetsova2015.inna.services.MasterService;
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
public class MyControllerMaster {

    @Autowired
    private MasterService masterService;

    @RequestMapping("/masters")
    public String masters(Model model) {
        model.addAttribute("masters", masterService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "masters";
    }

    @RequestMapping(value = "/add_master", method = RequestMethod.POST)
    public String addMaster(@RequestParam String surname, @RequestParam String name, @RequestParam String phone,
                            @RequestParam String status, Model model) {
        Master master = new Master(surname, name, phone, status);
        masterService.add(master);
        model.addAttribute("masters", masterService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "masters";
    }

    @RequestMapping(value = "/master_find_surname", method = RequestMethod.POST)
    public String findMasterSurname(@RequestParam String surname, Model model) {
        String message1 = "Success!";
        List<Master> list_find = new ArrayList<>();
        for (Master m : masterService.list()) {
            if (m.getSurname().equals(surname)) {
                list_find.add(m);
            }
        }
        if (list_find.size() == 0) {
            message1 = "No such master in our database!";
        }
        model.addAttribute("message1", message1);
        model.addAttribute("masters_find_surname", list_find);
        model.addAttribute("currentDate", MyController.curDate());
        return "masters_find_surname";
    }

    @RequestMapping(value = "/master_find_phone", method = RequestMethod.POST)
    public String findMasterPhone(@RequestParam String phone, Model model) {
        String message2 = "Success!";
        List<Master> list_find = new ArrayList<>();
        for (Master m : masterService.list()) {
            if (m.getPhone().equals(phone)) {
                list_find.add(m);
            }
        }
        if (list_find.size() == 0) {
            message2 = "No such master in our database!";
        }
        model.addAttribute("message2", message2);
        model.addAttribute("masters_find_phone", list_find);
        model.addAttribute("currentDate", MyController.curDate());
        return "masters_find_phone";
    }

    @RequestMapping("/change_status")
    public String changeStatus(@RequestParam int id_change, Model model) {
        for (Master m : masterService.list()) {
            if (m.getId() == id_change) {
                System.out.println(m.toString());
                masterService.changeStatus(m);
                System.out.println(m.toString());
            }
        }
        model.addAttribute("masters", masterService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "masters";
    }

    @RequestMapping("/delete_master")
    public String deleteMaster(@RequestParam int id_delete, Model model) {
        for (Master master : masterService.list()) {
            if (master.getId() == id_delete) {
                masterService.delete(id_delete);
            }
        }
        model.addAttribute("masters", masterService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "masters";
    }

    @RequestMapping("/changeMasterPhone")
    public String changeMasterPhone(@RequestParam int id_change, @RequestParam String newPhone, Model model) {
        for (Master master : masterService.list()) {
            if (master.getId() == id_change) {
                masterService.changePhone(id_change, newPhone);
            }
        }
        model.addAttribute("masters", masterService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "masters";
    }
}
