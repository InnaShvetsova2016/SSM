package com.gmail.shvetsova2015.inna.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String surname;
    private String name;
    private String phone;
    private Date lastVisitDate;

    public Client() {
    }

    public Client(String surname, String name, String phone) {
        this.surname = surname;
        this.name = name;
        this.phone = phone;
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

    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public String getSDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
        String d = dateFormat.format(this.lastVisitDate);
        return d;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    @Override
    public String toString() {
        System.out.println("id   surname   name  phone   lastVisitDate");
        return "Client[ " + id + " " + surname + " " + name + " " + phone + " " + lastVisitDate + "]";
    }
}

