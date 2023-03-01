package com.company;

import java.util.Map;

public class Doctor extends Person {
    public Doctor(){}

    public Doctor(Map<String, Object> details){
        setName(details.getOrDefault("name", "").toString());
        setLicenseNumber(details.getOrDefault("licenseNumber", "").toString());
        setEmail(details.getOrDefault("email", "").toString());
        setDateOfBirth(details.getOrDefault("dateOfBirth", "").toString());
        setContactNumber(details.getOrDefault("contactNumber", "").toString());
        setSpecialisation(Specialisation.findType(details.getOrDefault("specialisation", "").toString()));
    }


    private String licenseNumber;
    private Specialisation specialisation;

    @Override
    protected boolean anySubFieldNull() {
        return false;
    }


    public enum Specialisation {
        CosmeticDermatology ("Cosmetic Dermatology"),
        MedicalDermatology ("Medical Dermatology"),
        PaediatricDermatology ("Paediatric Dermatology");

        private String type;

        Specialisation(String type){
            this.type = type;
        }

        public String getSpecialisation() {
            return type;
        }

        public static Specialisation findType(String keyType){
            for (Specialisation value : Specialisation.values()) {
                if (keyType.contentEquals(value.type)) {
                    return value;
                }
            }
            return null;
        }

        public static String removeWhitespaces(String string) {
            return string.replace(" ", "");
        }

    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Specialisation getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

}
