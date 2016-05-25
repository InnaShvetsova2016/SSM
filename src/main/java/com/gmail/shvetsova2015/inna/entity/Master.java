package com.gmail.shvetsova2015.inna.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Masters")
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String surname;
    private String name;
    private String phone;
    private String status;
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<Order> list = new ArrayList<Order>();

    public Master() {
    }

    public Master(String surname, String name, String phone, String status) {
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }

    public void changeStatus() {
        if (this.getStatus().equals("active")) {this.setStatus("non-active");}
        else this.setStatus("active");
    }
    @Override
    public String toString() {
        System.out.println("id   surname   name    phone   status");
        return "Master[ " + id + " " + surname + " " + name + " " + phone + " " + status + "]";
    }
}
