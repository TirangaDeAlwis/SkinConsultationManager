package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewDetails extends JFrame implements ActionListener {
    // Create text fields for input
    private JTextField patientNameF = new JTextField(20);
    private JTextField docNameF = new JTextField(20);
    private JTextField constTimeF = new JTextField(20);
    private JTextField constDateF = new JTextField(20);
    private JTextField patientDobF = new JTextField(20);
    private JTextField remarkValue = new JTextField(20);
    JTextField patientId;
    JButton button = new JButton("Enter");

    public ViewDetails() {
        super("Personal Details Form");

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        // Add labels and text fields for each personal detail
        JLabel firstNameLabel = new JLabel("Enter your patient ID here : ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(firstNameLabel, constraints);
        constraints.gridx = 1;
        patientId = new JTextField(20);
        add(patientId, constraints);
        constraints.gridx = 2;
        button.addActionListener(this);
        add(button,constraints);

        JLabel surnameLabel = new JLabel("Patient Name: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(surnameLabel, constraints);
        constraints.gridx = 1;
        add(patientNameF, constraints);

        JLabel emailLabel = new JLabel("Doctor Name: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(emailLabel, constraints);
        constraints.gridx = 1;
        add(docNameF, constraints);

        JLabel dobLabel = new JLabel("Consultation Date: ");;
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(dobLabel, constraints);
        constraints.gridx = 1;
        add(constDateF, constraints);

        JLabel mobileLabel = new JLabel("Consultation Time: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(mobileLabel, constraints);
        constraints.gridx = 1;
        add(constTimeF, constraints);

        JLabel idLabel = new JLabel("Patient DOB: ");
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(idLabel, constraints);
        constraints.gridx = 1;
        add(patientDobF, constraints);



        JLabel remarkLabel = new JLabel("Patient Remark: ");
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(remarkLabel, constraints);
        constraints.gridx = 1;
        add(remarkValue, constraints);
        pack();
    }

    public static void main(String[] args) {
        ViewDetails viewDetails = new ViewDetails();
        viewDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewDetails.setLocationRelativeTo(null);
        viewDetails.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = patientId.getText();
        for (Consultation consultation : SkinConsultationManager.consultations) {
            if (consultation.getPatient().getId().equals(id)){
                patientNameF.setText(consultation.getPatient().getName()+" "+consultation.getPatient().getSurName());
                docNameF.setText(consultation.getDoctor().getName()+" "+consultation.getDoctor().getSurName());
                constDateF.setText(String.valueOf(consultation.getDate()));
                constTimeF.setText(String.valueOf(consultation.getTime()));
                patientDobF.setText(consultation.getPatient().getDateOfBirth());
                remarkValue.setText(consultation.getRemark());
                break;
            }
        }
    }
}

