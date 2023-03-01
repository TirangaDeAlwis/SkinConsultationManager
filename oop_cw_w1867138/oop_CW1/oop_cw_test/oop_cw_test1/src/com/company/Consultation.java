package com.company;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Consultation implements Serializable{
    @Serial
    private static final long serialVersionUID=1L;
    private Doctor doctor;
    private Patient patient;
    private LocalDate date;
    private LocalTime time;
    private String notes;
    private double price;
    private String remark;

    static ManageDetails instamce;

    public void setRemark(String remark){this.remark = remark;}
    public String getRemark(){return this.remark;}
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Consultation(Doctor doctor, Patient patient, LocalDate date, LocalTime time) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;

    }


    @Override
    public String toString() {
        return "Consultation{" +
                "doctor=" + doctor +
                ", patient=" + patient +
                ", date=" + date +
                ", time=" + time +
                ", notes='" + notes + '\'' +
                ", price=" + price +
                '}';
    }
}
