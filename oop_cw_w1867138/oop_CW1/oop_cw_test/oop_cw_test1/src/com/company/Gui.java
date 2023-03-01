package com.company;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Gui extends JFrame implements ActionListener {
    private static ManageDetails manager = new ManageDetails();

    private JLabel nameL,surNameL,dobL,mobileNoL,idL,dateL,monthL,yearL,minL,hourL,remark;
    private JTextField nameT,surNameT,dobT,mobileNoT,idT,remarkInput;

    DatePicker datePicker;
    TimePicker timePicker;
    JButton submit;

    Doctor doctor = ManageDetails.doctorArrayList.get(DoctorTable.selectedDoctor);
    public Gui(){
        setLayout(null);

        nameL=new JLabel("Name ");
        nameT=new JTextField(20);
        nameL.setBounds(10,20,150,25);
        nameT.setBounds(165,20,200,25);
        this.add(nameL);
        this.add(nameT);


        surNameL=new JLabel("Surname ");
        surNameT=new JTextField(20);
        surNameL.setBounds(10,60,150,25);
        surNameT.setBounds(165,60,200,25);
        add(surNameL);
        add(surNameT);

        dobL=new JLabel("DateOfBirth ");
        dobT=new JTextField(20);
        dobL.setBounds(10,100,150,25);
        dobT.setBounds(165,100,200,25);
        add(dobL);
        add(dobT);

        mobileNoL=new JLabel("mobileNumber ");
        mobileNoT=new JTextField(20);
        mobileNoL.setBounds(10,140,150,25);
        mobileNoT.setBounds(165,140,200,25);
        add(mobileNoL);
        add(mobileNoT);

        idL=new JLabel("Id  ");
        idT=new JTextField(20);
        idL.setBounds(10,180,150,25);
        idT.setBounds(165,180,200,25);
        add(idL);
        add(idT);

        dateL=new JLabel("Select a date for consultation");
        datePicker = new DatePicker();
        dateL.setBounds(10,240,250,25);
        datePicker.setBounds(180,240,200,25);
        add(dateL);
        add(datePicker);

        monthL=new JLabel("Select a time for consultation");
        timePicker=new TimePicker();
        monthL.setBounds(10,270,250,25);
        timePicker.setBounds(180,270,200,25);
        add(monthL);
        add(timePicker);

        remark=new JLabel("Patient Remark ");
        remarkInput=new JTextField(20);
        remark.setBounds(10,300,150,25);
        remarkInput.setBounds(180,300,200,25);
        this.add(remark);
        this.add(remarkInput);

        submit=new JButton("submit");
        submit.setBounds(150,400,100,25);
        add(submit);
        submit.addActionListener(this);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("WestminsterSkinConsultationCenter..");
        setSize(500,500);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public static void main(String[] args) {

        Gui nn=new Gui();
        nn.setSize(500,500);
        nn.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name=  nameT.getText();
        String surName=  surNameT.getText();
        String dob=  dobT.getText();
        String id=  idT.getText();
        String mobile=  mobileNoT.getText();
        LocalDate lookingDate = datePicker.getDate();
        LocalTime lookingTime = timePicker.getTime();
        String remarkI = remarkInput.getText();

        Patient d= new Patient(name, surName, mobile, dob,id);
        boolean available = true;

        if (SkinConsultationManager.consultations.size() == 0) {
            available = true;

        }
        else {
            for (Consultation consultation : SkinConsultationManager.consultations) {
                if (!consultation.getDoctor().getLicenseNumber().equals(doctor.getLicenseNumber()) || !consultation.getDate().equals(lookingDate) || !consultation.getTime().equals(lookingTime)) {
                    continue;
                }
                available = false;
            }
        }

        if (!available) {
            JOptionPane.showMessageDialog(null, "Doctor is not available at that date or time\nTry a different date or time", "Doctor is not available",JOptionPane.INFORMATION_MESSAGE);
            submit.setVisible(false);
        }
        else {
            Consultation newConsultation = new Consultation(doctor,d, lookingDate, lookingTime);
            newConsultation.setRemark(remarkI);
            SkinConsultationManager.consultations.add(newConsultation);
            JOptionPane.showMessageDialog(null, "Consultation added for Dr. "+doctor.getName()+" "+doctor.getSurName()+" Successfully.\n\nDate : "+lookingDate+"\nTime : "+lookingTime+"", "Consultation added successfully", JOptionPane.PLAIN_MESSAGE);
        }

        try {
            WestminsterSkinConsultationManager.saveFile("Doctors");
            WestminsterSkinConsultationManager.saveAllConsultations("Consultations");
        } catch (IOException ignored) {

        }






        }


    }
