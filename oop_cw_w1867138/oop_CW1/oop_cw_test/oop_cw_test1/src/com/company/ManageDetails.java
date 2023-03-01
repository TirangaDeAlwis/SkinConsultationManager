package com.company;

import java.io.*;
import java.util.*;

import static com.company.Consultation.instamce;
import static com.company.WestminsterSkinConsultationManager.isContactNumberValid;
import static com.company.WestminsterSkinConsultationManager.isEmailValid;


public class ManageDetails
{
    public static Map<String, Doctor> doctors;
    public static ArrayList<Doctor> doctorArrayList = new ArrayList<>();
    public Doctor getDoctor(String licenseNumber) {
        return doctors.get(licenseNumber);
    }
    public Patient getPatient(String id) {
        return patients.get(id);
    }
    public int getDoctorCount() {
        return doctors.size();
    }
    public void addNewDoctor(Doctor doctor)
    {
        if (doctors.size() == 10) {
            throw new RuntimeException("Maximum number of doctors are allocated, Please remove an existing doctor to add a new doctor!");
        } else if (doctor.anyFieldNull()) {
            throw new RuntimeException("Required information are missing about the doctor, Please provide them and continue!");
        } else if (doctors.containsKey(doctor.getLicenseNumber())) {
            throw new RuntimeException("Doctor with license number [" + doctor.getLicenseNumber() + "] is already registered in the system!");
        } else if (!isContactNumberValid(doctor.getContactNumber())) {
            throw new RuntimeException("Contact number is not in valid format. Please enter the number starting with 94!");
        } else if (!isEmailValid(doctor.getEmail())) {
            throw new RuntimeException("Email is not in valid format!");
        }

        doctors.put(doctor.getLicenseNumber(), doctor);
        anyChanges = true;
    }


    public Doctor removeDoctor(String licenseNumber) {
        if (hasLicenseNumber(licenseNumber)) {
            Doctor doctor = getDoctor(licenseNumber);
            removeConsultations(doctor);
            doctors.remove(licenseNumber);
            anyChanges = true;
            return doctor;
        } else {
            throw new RuntimeException("No Doctor found with this number -> " + licenseNumber + "!");
        }
    }

    public void removeConsultations(Doctor doctor) {
        int preCount = consultations.size();
        Iterator<Consultation> iterator = consultations.iterator();
        while (iterator.hasNext()) {
            Consultation consultation = iterator.next();
            if (consultation.getDoctor().getLicenseNumber().contentEquals(doctor.getLicenseNumber())) {
                iterator.remove();
            }
        }

        if (preCount != consultations.size()) {
            anyChanges = true;
        }
    }

    public void clearAllDoctors() {
        doctors.clear();
        consultations.clear();
        anyChanges = true;
    }

    public boolean hasLicenseNumber(String licenseNumber) {
        return doctors.containsKey(licenseNumber);
    }


    enum DataTypes {
        Doctors("Doctors"),
        Patients("Patients"),
        Consultations("Consultations");

        private final String type;
        DataTypes(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public ManageDetails() {}

    public static Map<String, Patient> patients;

    public static List<Consultation> consultations;

    {
        doctors = new HashMap<>();
        patients = new HashMap<>();
        consultations = new ArrayList<>();
        anyChanges = false;
    }
    public static synchronized ManageDetails getInstance() {
        if (instamce == null) {
            instamce = new ManageDetails();
            instamce.findData();
        }
        return instamce;
    }

    void findData(){
        File file = new File("details.txt");

        if (file.exists()){
            try {
                FileInputStream input = new FileInputStream(file);
                BufferedReader finder = new BufferedReader(new InputStreamReader(input));
                StringBuilder stringBuilder = new StringBuilder();
                try {

                    while (true) {
                        String line = finder.readLine();
                        if (line == null) {
                            break;
                        }
                        stringBuilder.append(line);
                    }

                    finder.close();
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Doctor> getDoctors() {
        return new ArrayList<>(doctors.values());
    }

    public static String toJsonFromObject(Object object) {
        return "";
    }

    private static boolean changes;


    public static void SaveDetails() {


        Map<String, Object> data = new HashMap<>();
        data.put(DataTypes.Doctors.getType(), doctors);
        data.put(DataTypes.Patients.getType(), patients);
        data.put(DataTypes.Consultations.getType(), consultations);
        try{
            File file = new File("details.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(toJsonFromObject(data).getBytes());
            fos.flush();
            fos.close();

            changes = false;
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static boolean anyChanges;

    public boolean anyChanges() {
        return anyChanges;
    }

}
