package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.company.Doctor.Specialisation.removeWhitespaces;
import static com.company.ManageConsole.no;
import static com.company.ManageConsole.yes;
import static com.company.ManageDetails.doctors;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    private ManageConsole manageConsole;
    private ManageDetails manageDetails;

    public void runner() throws IOException {
        manageConsole = ManageConsole.createConsole();
        manageDetails = ManageDetails.getInstance();

        try {
            WestminsterSkinConsultationManager.loadAllConsultations("Consultations");
            WestminsterSkinConsultationManager.loadData("Doctors");
        } catch (Exception ignored) {

        }

        showMenu();

    }

    private void showMenu() throws IOException {
        manageConsole.displayMenu();
        menu(manageConsole.getUserSelectedOption());
    }


    private void menu(ManageConsole.Options option) throws IOException {

        if (option == ManageConsole.Options.addDoctor) {
            new AddDoctor().start();
        } else if (option == ManageConsole.Options.deleteDoctor) {
            new DeleteDoctor().deleteOne();
        } else if (option == ManageConsole.Options.deleteAllDoctors) {
            new DeleteDoctor().deleteAll();
        } else if (option == ManageConsole.Options.showAllDoctors) {
            new ShowDoctors().showAllInfo();
        } else if (option == ManageConsole.Options.saveChanges) {
            saveFile("Doctors");
            saveAllConsultations("Consultations");
            manageConsole.success("Saved!");
        } else if (option == ManageConsole.Options.openGUI) {
            // call GUI jar
            DoctorTable.main(null);
        } else {
            boolean isDone;
            do {
                isDone = false;
                System.out.print("\n(yes/ no): ");
                String input = manageConsole.getUserInput();
                switch (input.replace(" ", "").toLowerCase()) {
                    case "yes":
                        isDone = true;
                        manageDetails.SaveDetails();
                        manageConsole.success("Saved!");
                        break;
                    case "no":
                        isDone = false;
                        break;
                    default:
                        System.out.println("Invalid value!");
                        ;
                }

            } while (!isDone);
            System.exit(0);

        }
        showMenu();
    }

    public static void saveFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        for (Doctor newDoctor : ManageDetails.doctorArrayList) {
            objOut.writeObject(newDoctor);
        }
        System.out.println("\nData saved successfully\n");
    }

    public static void saveAllConsultations(String fileName) throws IOException {
        File file = new File(fileName);
        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

        for (Consultation consultation : SkinConsultationManager.consultations) {
            objOut.writeObject(consultation);
        }
    }

    public static void loadData(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        while (true) {
            try {
                Doctor newDoctor = (Doctor) objectInputStream.readObject();
                ManageDetails.doctorArrayList.add(newDoctor);
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }

    }

    public static void loadAllConsultations(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        while (true) {
            try {
                Consultation consultation = (Consultation) objectInputStream.readObject();
                SkinConsultationManager.consultations.add(consultation);
            } catch (IOException | ClassNotFoundException e) {
                break;
            }
        }
    }

    public int getDoctorCount() {
        return doctors.size();
    }


    public class AddDoctor {

        Doctor start() throws IOException {
            if (manageDetails.getDoctorCount() == 10) {
                System.out.println("Please remove an existing Doctor to add a new Doctor!");
                showMenu();
                return null;
            }

            System.out.println("Submit required Details to onboard a Doctor, Type \"exit\" to cancel the operation!");
            String licenseNumber = getLicenseNumber();
            if (licenseNumber == null) {
                return null;
            }

            Doctor.Specialisation specialisation = getSpecialisation();
            if (specialisation == null) {
                return null;
            }

            String name = getName();
            if (name == null) {
                return null;
            }

            String surName = getSurname();
            if (surName == null) {
                return null;
            }

            String email = getEmail();
            if (email == null) {
                return null;
            }

            String contactNumber = getContactNumber();
            if (contactNumber == null) {
                return null;
            }

            String dateOfBirth = getDateOfBBirth();
            if (dateOfBirth == null) {
                return null;
            }

            Doctor doctor = new Doctor();
            doctor.setLicenseNumber(licenseNumber);
            doctor.setSpecialisation(specialisation);
            doctor.setName(name);
            doctor.setSurName(surName);
            doctor.setEmail(email);
            doctor.setContactNumber(contactNumber);
            doctor.setDateOfBirth(dateOfBirth);

            manageDetails.addNewDoctor(doctor);
            ManageDetails.doctorArrayList.add(doctor);
            System.out.println("A New Doctor added!");
            return doctor;
        }
    }

    private String getLicenseNumber() {
        String licenseNumber;
        while (true) {
            licenseNumber = ManageConsole.customInput("Enter License Number: ");
            if (isExitTypedAsInput(licenseNumber)) {
                System.out.println("Process was cancelled!");
                return null;
            } else if (manageDetails.hasLicenseNumber(licenseNumber)) {
                System.out.println("This number already exists!");
            } else {
                break;
            }
        }
        return licenseNumber;
    }

    private Doctor.Specialisation getSpecialisation() {
        String specialisation;
        Doctor.Specialisation type;

        System.out.println("Available Specialisations [");
        for (Doctor.Specialisation value : Doctor.Specialisation.values()) {
            System.out.println("    " + value.getSpecialisation() + ",");
        }
        System.out.println("]");

        while (true) {
            specialisation = ManageConsole.customInput("Enter Specialisation: ");
            type = Doctor.Specialisation.findType(specialisation);

            if (isExitTypedAsInput(specialisation)) {
                System.out.println("Process was cancelled!");
                return null;
            } else if (type == null) {
                System.out.println("Specialisation Invalid!");
            } else {
                break;
            }
        }
        return type;
    }

    public static boolean isEmptyString(String string) {
        return string.replace(" ", "").isEmpty();
    }

    public static boolean onlyNumbers(String prm) {
        return prm.length() > 0 && prm.matches("[0-9]*");
    }

    private String getName() {
        String name;
        while (true) {
            name = ManageConsole.customInput("Doctor's Name: ");
            if (isExitTypedAsInput(name)) {
                System.out.println("Process was cancelled!");
                return null;
            } else if (isEmptyString(name)) {
                System.out.println("Name is empty!");
            } else if (onlyNumbers(name)) {
                System.out.println("Name is only digits!");
            } else {
                break;
            }
        }
        return name;
    }

    private String getSurname() {
        String name;
        while (true) {
            name = manageConsole.customInput("Doctor's Surname: ");
            if (isExitTypedAsInput(name)) {
                System.out.println("Process was cancelled!");
                return null;
            } else if (isEmptyString(name)) {
                System.out.println("Surname is empty!");
            } else if (onlyNumbers(name)) {
                System.out.println("Surname is only digits!");
            } else {
                break;
            }
        }
        return name;
    }

    public static boolean isEmailValid(String email) {
        return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }

    private String getEmail() {
        String email;
        while (true) {
            email = manageConsole.customInput("Email: ");
            if (isExitTypedAsInput(email)) {
                System.out.println("Process was cancelled!");
                return null;
            } else if (isEmailValid(email)) {
                break;
            } else {
                System.out.println("Invalid email!");
            }
        }
        return email;
    }

    public static boolean isContactNumberValid(String contactNumber) {
        return contactNumber.matches("94\\d{9}");
    }

    private String getContactNumber() {
        String contactNumber;
        while (true) {
            contactNumber = manageConsole.customInput("Contact Number: ");
            if (isExitTypedAsInput(contactNumber)) {
                System.out.println("Process was cancelled!");
                return null;
            } else if (isContactNumberValid(contactNumber)) {
                break;
            } else {
                System.out.println("Invalid Contact Number!");
            }
        }
        return contactNumber;
    }

    public static final String dateFormat = "yyyy-MM-dd";

    private static final SimpleDateFormat FormatTheDate = new SimpleDateFormat(dateFormat);


    public static Date parseDate(String dateText) throws ParseException {
        return FormatTheDate.parse(dateText);
    }

    private String getDateOfBBirth() {
        String dateOfBirth;
        while (true) {
            dateOfBirth = manageConsole.customInput("Date Of Birth (yyyy-MM-dd): ");
            if (isExitTypedAsInput(dateOfBirth)) {
                System.out.println("Process was cancelled!");
                return null;
            }

            try {
                parseDate(dateOfBirth);
                break;
            } catch (ParseException e) {
                System.out.println("Date or Format is Invalid!");
            }
        }
        return dateOfBirth;
    }

    private boolean isExitTypedAsInput(String input) {
        return ManageConsole.Options.stop.getValue().contentEquals(input.replace(" ", "").toUpperCase());
    }

    public class DeleteDoctor {

        Doctor deleteOne() {
            String licenseNumber = getLicenseNumber();
            if (licenseNumber == null) {
                return null;
            }

            Doctor doctor = manageDetails.removeDoctor(licenseNumber);
            System.out.println("Dr. " + doctor.getName() + " removed!");
            return doctor;
        }

        boolean deleteAll() {
            boolean deleteAll = getConfirmation();
            if (!deleteAll) {
                return false;
            }

            manageDetails.clearAllDoctors();
            System.out.println("All the doctors and consultations are removed!");
            return true;
        }

        public boolean hasLicenseNumber(String licenseNumber) {
            return doctors.containsKey(licenseNumber);
        }

        private String getLicenseNumber() {
            String licenseNumber;
            while (true) {
                licenseNumber = manageConsole.customInput("License Number: ");
                if (isExitTypedAsInput(licenseNumber)) {
                    System.out.println("Process was cancelled!");
                    return null;
                } else if (manageDetails.hasLicenseNumber(licenseNumber)) {
                    break;
                } else {
                    System.out.println("No Doctor found with this number!");
                }
            }
            return licenseNumber;
        }


        private boolean getConfirmation() {
            String input;
            while (true) {
                input = manageConsole.customInput("Do you want to remove all the doctors (yes/ no): ");
                if (isExitTypedAsInput(input)) {
                    System.out.println("Operation was cancelled by the user!");
                    return false;
                } else if (removeWhitespaces(input).toLowerCase().contentEquals(yes)) {
                    return false;
                } else if (removeWhitespaces(input).toLowerCase().contentEquals(no)) {
                    break;
                } else {
                    System.out.println("Invalid Confirmation!");
                }
            }
            return true;
        }
    }

    public class ShowDoctors {

        private boolean viewAll() {
            boolean showInfo = showAllInfo();
            return showInfo;
        }

        boolean showAllInfo() {
            System.out.println("Found " + manageDetails.getDoctorCount() + " Doctors!");
            for (Doctor doctor : manageDetails.getDoctors()) {
                System.out.println("-- " + doctor.getName() + " " + doctor.getSurName() + " license number - " + doctor.getLicenseNumber());
            }

            while (true) {
                String licenseNumber = manageConsole.customInput("Enter License number of doctor to view full info, \"Q\" to go to menu: ");
                if (isExitTypedAsInput(licenseNumber)) {
                    return false;
                } else if (manageDetails.hasLicenseNumber(licenseNumber)) {
                    showInfo(licenseNumber);
                } else {
                    System.out.println("License number is Invalid!");
                }
            }
        }

        private void showInfo(String licenseNumber) {
            Doctor doctor = manageDetails.getDoctor(licenseNumber);
            System.out.println("-- Name              : " + doctor.getName());
            System.out.println("-- Surname           : " + doctor.getSurName());
            System.out.println("-- License Number    : " + doctor.getLicenseNumber());
            System.out.println("-- Specialisation    : " + doctor.getSpecialisation().getSpecialisation());
            System.out.println("-- Email             : " + doctor.getEmail());
            System.out.println("-- Contact Number    : " + doctor.getContactNumber());
            System.out.println("-- Date Of Birth     : " + doctor.getDateOfBirth());
        }

    }
}