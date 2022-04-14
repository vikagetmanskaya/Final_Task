package com.example.demo1.entity;

import java.sql.Date;

public class User extends AbstractEntity {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public User(String email, String password, String firstName, String lastName, Date dateOfBirth) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

}