package com.gmail.shvetsova2015.inna.entity;

public class Salary {
    private Master m;
    private int count;
    private double profit;
    private double salary;

    public Salary() {
    }

    public Master getM() {
        return m;
    }

    public String getMasterName() {return m.getName();
    }

    public void setM(Master m) {
        this.m = m;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "m=" + m +
                ", count=" + count +
                ", profit=" + profit +
                ", salary=" + salary +
                '}';
    }
}
