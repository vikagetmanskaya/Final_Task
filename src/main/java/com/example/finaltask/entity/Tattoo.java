package com.example.finaltask.entity;

import java.util.Date;

public class Tattoo extends AbstractEntity{
    private long id;
    private String name;
    private String firstNameMaster;
    private String lastNameMaster;
    private int size;
    private Date dateAdded;
    private double rating;

    public Tattoo(String name, String firstNameMaster, String lastNameMaster, int size, Date dateAdded, double rating) {
        this.name = name;
        this.firstNameMaster = firstNameMaster;
        this.lastNameMaster = lastNameMaster;
        this.size = size;
        this.dateAdded = dateAdded;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstNameMaster() {
        return firstNameMaster;
    }

    public String getLastNameMaster() {
        return lastNameMaster;
    }

    public int getSize() {
        return size;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public double getRating() {
        return rating;
    }
}
