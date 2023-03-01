package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoctorTable extends JFrame implements ActionListener {
    JTable table;

    public static int selectedDoctor;
    JButton view;
    String[] columnNames = {"Full Name", "Date of Birth", "Specialisation", "Contact Number"};
    String[][] docDetails = new String[ManageDetails.doctorArrayList.size()][4];
    Font  font1  = new Font("Verdana", Font.PLAIN, 12);
    Font font2 = new Font("Ariel", Font.BOLD, 30);

    public DoctorTable(){
        int count = 0;
        for (Doctor newDoctor : ManageDetails.doctorArrayList) {
            String[] details = new String[4];
            details[0] = newDoctor.getName()+ " " +newDoctor.getSurName();
            details[1] = String.valueOf(newDoctor.getDateOfBirth());
            details[2] = String.valueOf(newDoctor.getSpecialisation());
            details[3] = newDoctor.getContactNumber();
            docDetails[count] = details;
            count++;
        }
        Container container = getContentPane();
        container.setLayout(new FlowLayout());

        JLabel heading = new JLabel("List of Doctors");
        heading.setFont(font2);
        add(heading);

        table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(docDetails, columnNames){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table.setFont(font1);
        table.setModel(tableModel);
        table.setRowHeight(40);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900,350));
        table.setGridColor(Color.BLACK);
        add(scrollPane);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(3,1));
        JPanel firstRow = new JPanel();
        firstRow.setPreferredSize(new Dimension(1100,100));

        for (int i=0; i<ManageDetails.doctorArrayList.size(); i++){
            JButton button = new JButton("Add Consultation for Dr. "+ManageDetails.doctorArrayList.get(i).getName().toUpperCase()+" "+ManageDetails.doctorArrayList.get(i).getSurName().toUpperCase()+" ");
            button.setActionCommand(""+i+"");
            firstRow.add(button);
            button.addActionListener(this);
        }
        view = new JButton("View Saved Consultation");
        view.setActionCommand("View");
        view.addActionListener(this);
        firstRow.add(view);
        lowerPanel.add(firstRow);

        add(lowerPanel);
    }

    public static void main(String[] args) {

        DoctorTable viewTable = new DoctorTable();
        viewTable.setSize(1100,700);
        viewTable.setLocationRelativeTo(null);
        viewTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewTable.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonLabel = e.getActionCommand();
        switch (buttonLabel) {
            case "0" -> {
                selectedDoctor = 0;
                Gui.main(null);

            }
            case "1" -> {
                selectedDoctor = 1;
                Gui.main(null);

            }
            case "2" -> {
                selectedDoctor = 2;
                Gui.main(null);
            }
            case "3" -> {
                selectedDoctor = 3;
                Gui.main(null);
            }
            case "4" -> {
                selectedDoctor = 4;
                Gui.main(null);
            }
            case "5" -> {
                selectedDoctor = 5;
                Gui.main(null);
            }
            case "6" -> {
                selectedDoctor = 6;
                Gui.main(null);
            }
            case "7" -> {
                selectedDoctor = 7;
                Gui.main(null);
            }
            case "8" -> {
                selectedDoctor = 8;
                Gui.main(null);
            }
            case "9" -> {
                selectedDoctor = 9;
                Gui.main(null);
            }
            case "View" -> {
                ViewDetails.main(null);
            }
        }

    }
}
