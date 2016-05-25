package com.gmail.shvetsova2015.inna.entity;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Master master;
    private Date date;
    private String time; // from "13:30"
    private String status;
    private double amount;

    public Order() {
    }

    public Order(Client client, Product product, Master master, Date date, String time) {
        this.client = client;
        this.product = product;
        this.master = master;
        this.date = date;
        this.time = time;
        this.status = "non-paid";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public String getClientSN() {
        return this.getClient().getSurname() + " " + this.getClient().getName();
    }

    public String getClientPhone() {
        return this.getClient().getPhone();
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getService() {
        return product;
    }

    public String getServiceType() {
        return this.getService().getType();
    }

    public void setService(Product product) {
        this.product = product;
    }

    public Master getMaster() {
        return master;
    }

    public String getMasterName() {
        return this.getMaster().getName();
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Date getDate() {
        return date;
    }

    public String getSDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
        return dateFormat.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        System.out.println("id  clientId   serviceId  masterId  date timeFrom  timeLimit");
        return "Order[ " + id + " " + client.getId() + " " + product.getId() + " " + master.getId() + " " +
                date + " " + time + " " + product.getTimeLimit() + "]";
    }

    @Override
    public int compareTo(Object obj) {
        Order tmp = (Order) obj;
        int result = date.compareTo(tmp.date);
        if(result != 0) {
            return result;
        }
        result = time.compareTo(tmp.time);
        if(result != 0) {
            return (int) result / Math.abs( result );
        }
        return 0;
    }
}
