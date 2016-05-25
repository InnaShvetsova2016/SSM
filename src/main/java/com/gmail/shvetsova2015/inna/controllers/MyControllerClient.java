package com.gmail.shvetsova2015.inna.controllers;

import com.gmail.shvetsova2015.inna.entity.Client;
import com.gmail.shvetsova2015.inna.services.ClientService;
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
public class MyControllerClient {

    @Autowired
    private ClientService clientService;

    @RequestMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", clientService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "clients";
    }

    @RequestMapping("/changeClientPhone")
    public String changeClientPhone(@RequestParam int id_change, @RequestParam String newPhone, Model model) {
        for (Client client : clientService.list()) {
            if (client.getId() == id_change) {
                clientService.changePhone(id_change, newPhone);
            }
        }
        model.addAttribute("clients", clientService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "clients";
    }

    @RequestMapping("/delete_client")
    public String deleteClient(@RequestParam int id_delete, Model model) {
        String message = "Client was deleted successfully!";
        for (Client client : clientService.list()) {
            if (client.getId() == id_delete) {
                try {
                    clientService.delete(id_delete);
                } catch (Exception ex) {
                    message = "Client cannot be deleted! There are active orders in database!";
                }
            }
        }
        model.addAttribute("warningmessage", message);
        model.addAttribute("clients", clientService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "clients";
    }

    @RequestMapping(value = "/add_client", method = RequestMethod.POST)
    public String addClient(@RequestParam String surname, @RequestParam String name, @RequestParam String phone, Model model) {
        Client client = new Client(surname, name, phone);
        clientService.add(client);
        model.addAttribute("clients", clientService.list());
        model.addAttribute("currentDate", MyController.curDate());
        return "clients";
    }

    @RequestMapping(value = "/client_find_surname", method = RequestMethod.POST)
    public String findClientSurname(@RequestParam String surname, Model model) {
        String message1 = "Success!";
        List<Client> list_find = new ArrayList<>();
        for (Client c : clientService.list()) {
            if (c.getSurname().equals(surname)) {
                list_find.add(c);
            }
        }
        if (list_find.size() == 0) {
            message1 = "No such client in our database!";
        }
        model.addAttribute("message1", message1);
        model.addAttribute("clients_find_surname", list_find);
        model.addAttribute("currentDate", MyController.curDate());
        return "clients_find_surname";
    }

    @RequestMapping(value = "/client_find_phone", method = RequestMethod.POST)
    public String findClientPhone(@RequestParam String phone, Model model) {
        String message2 = "Success!";
        List<Client> list_find = new ArrayList<>();
        for (Client c : clientService.list()) {
            if (c.getPhone().equals(phone)) {
                list_find.add(c);
            }
        }
        if (list_find.size() == 0) {
            message2 = "No such client in our database!";
        }
        model.addAttribute("message2", message2);
        model.addAttribute("clients_find_phone", list_find);
        model.addAttribute("currentDate", MyController.curDate());
        return "clients_find_phone";
    }
}
