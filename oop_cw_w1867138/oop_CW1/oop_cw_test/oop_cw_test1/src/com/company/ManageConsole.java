package com.company;

import java.util.HashSet;
import java.util.Scanner;

public class ManageConsole {
    public static final String selectOption = "Select an Option: ";
    public static final String invalidOption = "Invalid option selected";
    public static final String retryOption = invalidOption + ", Please try again: ";
    public static final String yes = "yes";
    public static final String no = "no";
    private final HashSet<Object> options;


    public enum Options {
        addDoctor("A"),
        deleteDoctor("D"),
        deleteAllDoctors("C"),
        showAllDoctors("S"),
        saveChanges("F"),
        openGUI("O"),
        stop("Q");

        private String value;

        Options(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }


        public static Options convertValueToOption(String option) {
            option = option.replace(" ", "").toUpperCase();
            for (Options consoleOptions : Options.values()) {
                if (consoleOptions.getValue().contentEquals(option)) {
                    return consoleOptions;
                }
            }
            return null;
        }
    }


    private HashSet<String> values;



    private ManageConsole(){
        this.options = new HashSet<>();


        for (Options option : Options.values()){
            options.add(option.getValue());
        }
    }

    public static ManageConsole createConsole() {
        return new ManageConsole();
    }

    public void displayMenu(){
        System.out.println(
                "\n==============================================================\n" +
                        "               Skin Consultation Centre Menu \n" +
                        "==============================================================\n\n" +
                        "       A - Add new Doctor\n" +
                        "       D - Delete a Doctor\n" +
                        "       C - Delete All Doctors\n" +
                        "       S - Show All Doctors\n" +
                        "       F - Save\n" +
                        "       O - Open GUI\n" +
                        "       Q - Quit\n\n" +
                        "==============================================================\n\n"
        );
    }

    public static String customInput(String inputText) {
        System.out.print(inputText);
        Scanner Input = new Scanner(System.in);

        return Input.nextLine();
    }

    public Options getUserSelectedOption() {

        Scanner Input = new Scanner(System.in);

        System.out.print(selectOption);
        String userInput = Input.nextLine();
        if (!isOptionValid(userInput)) {
            while (true) {
                userInput = customInput(retryOption);
                if (isOptionValid(userInput)) {
                    break;
                }
            }
        }
        return Options.convertValueToOption(userInput);
    }

    private boolean isOptionValid(String userInput) {
        return options.contains(userInput.toUpperCase().replace(" ", ""));
    }


    public void success(String successMessage) {
        System.out.println("*Success - " + successMessage);
    }

    public void warning(String warningMessage) {
        System.out.println("*Warning - " + warningMessage);
    }

    public String getUserInput() {
        Scanner Input = new Scanner(System.in);
        return Input.nextLine();
    }

}
