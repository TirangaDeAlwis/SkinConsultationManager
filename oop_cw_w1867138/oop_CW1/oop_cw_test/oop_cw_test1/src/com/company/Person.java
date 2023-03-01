package com.company;

import java.io.Serial;
import java.io.Serializable;

public abstract class Person implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;
    private String name;
    private String surName;
    private String email;
    private String contactNumber;
    private String dateOfBirth;

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    protected abstract boolean anySubFieldNull();

    public Person(String name, String surName, String contactNumber, String dateOfBirth) {
        this.name = name;
        this.surName = surName;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public boolean anyFieldNull() {
        if (getContactNumber() == null || getContactNumber().replace(" ", "").contentEquals("") ||
                getEmail() == null || getEmail().replace(" ", "").contentEquals("") ||
                getName() == null || getName().replace(" ", "").contentEquals("") ||
                getDateOfBirth() == null || getDateOfBirth().replace(" ", "").contentEquals("") ||
                getSurName() == null || getSurName().replace(" ", "").contentEquals("")
        ) {
            return true;
        }
        return anySubFieldNull();
    }
}
