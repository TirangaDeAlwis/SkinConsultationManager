package com.company;

import java.util.Map;


public class Patient extends Person {
    private String id;

    @Override
    protected boolean anySubFieldNull() {
        return getId() == null || getId().replace(" ", "").contentEquals("");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Patient(Map<String, Object> details){
        super();
        setId(details.getOrDefault("name", "").toString());
        setName(details.getOrDefault("name", "").toString());
        setSurName(details.getOrDefault("name", "").toString());
        setContactNumber(details.getOrDefault("name", "").toString());
        setEmail(details.getOrDefault("name", "").toString());
        setDateOfBirth(details.getOrDefault("name", "").toString());
    }

    public Patient(String name, String surName, String contactNumber, String dateOfBirth, String id) {
        super(name, surName, contactNumber, dateOfBirth);
        this.id = id;
    }
}
