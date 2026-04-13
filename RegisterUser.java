package com.mycompany.registeruser;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Enhanced RegisterUser class for NetBeans.
 * @author Student
 */
public class RegisterUser {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellNumber;

    // Methods for logic validation
    public boolean checkUserName(String username) {
        this.username = username; // Update internal state for later use
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        this.password = password; // Update internal state for later use
        boolean hasMinLength = password.length() >= 8;
        boolean hasCapital = Pattern.matches(".*[A-Z].*", password);
        boolean hasNumber = Pattern.matches(".*\\d.*", password);
        boolean hasSpecialChar = Pattern.matches(".*[^A-Za-z0-9].*", password);

        return hasMinLength && hasCapital && hasNumber && hasSpecialChar;
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        this.cellNumber = cellNumber; // Update internal state for later use
        // Matches South African format +27 followed by 6, 7, or 8 and 8 digits
        return Pattern.matches("^\\+27[678]\\d{8}$", cellNumber);
    }

    public String registerUser(String username, String password, String phone, String first, String last) {
        this.firstName = first;
        this.lastName = last;
        
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        return "The two above conditions have been met and the user has been registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        return this.username != null && this.username.equals(username) && 
               this.password != null && this.password.equals(password);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RegisterUser user = new RegisterUser();

        System.out.println("--- CREATE YOUR ACCOUNT ---");

        System.out.print("Enter First Name: ");
        String fName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lName = scanner.nextLine();

        System.out.print("Enter Username: ");
        String uName = scanner.nextLine();

        System.out.print("Enter Password: ");
        String pWord = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        // Register and show result
        System.out.println("\n" + user.registerUser(uName, pWord, phone, fName, lName));

        // Sign In process
        System.out.println("\n--- SIGN IN ---");
        System.out.print("Username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Password: ");
        String loginPass = scanner.nextLine();

        boolean status = user.loginUser(loginUser, loginPass);
        System.out.println(user.returnLoginStatus(status));
    }
}    