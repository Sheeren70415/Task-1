package org.example;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Login {

    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Boolean: checkPasswordComplexity()
    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    // Boolean: checkCellPhoneNumber()
    public static boolean checkCellPhoneNumber(String phoneNumber) {
        // Assuming international format includes '+' and correct length is 13 (e.g., +123456789012)
        String regex = "^\\+27[1-9][0-9]{8}$";
        if (Pattern.matches(regex, phoneNumber)) {
            return true;
        } else {
            return false;
        }

    }

    // String registerUser()
    public String registerUser(String username, String password, String phoneNumber) {
        boolean validUsername = checkUserName(username);
        boolean validPassword = checkPasswordComplexity(password);

        if (!validUsername) {
            return "Username is incorrectly formatted.";
        }
        if (!validPassword) {
            return "Password does not meet complexity requirements.";
        }
        // Assuming registration succeeds if checks pass
        return "User has been registered successfully.";
    }

    // Boolean loginUser()
    public boolean loginUser(String username, String password, String storedUsername, String storedPassword) {
        return username.equals(storedUsername) && password.equals(storedPassword);
    }

    // String returnLoginStatus
    public String returnLoginStatus(boolean loginSuccess) {
        return loginSuccess ? "Successful login." : "Failed login.";
    }
    public static void main(String[] args) {
        // Sample user data (in a real app, this would be in a database)
        Map<String, User> users = new HashMap<>();
        users.put("john_doe", new User("John", "Doe", "password123"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone number(only South African)");
        String phone = scanner.nextLine();

        boolean checkusername = checkUserName(username);
        boolean checkpassword = checkPasswordComplexity(password);
        boolean checkphone = checkCellPhoneNumber(phone);

        if (checkusername && checkpassword && checkphone){
            users.put(username , new User(username, phone, password));
        }

        System.out.println(checkusername ? "Username successfully captured." : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        System.out.println(checkpassword ? "Password successfully captured." : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        System.out.println(checkpassword ? "Cell phone number successfully added." : "Cell phone number incorrectly formatted or does not contain international code.");


        System.out.print("Enter username: ");
        String username1 = scanner.nextLine();
        System.out.print("Enter password: ");
        String password1 = scanner.nextLine();



        if (authenticateUser(users, username1, password1)) {
            User user = users.get(username);
            System.out.println("Welcome " + user.getUsername() + " " + user.getLastName() + ", it is great to see you again.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }

    private static boolean authenticateUser(Map<String, User> users, String username, String password) {
        if (users.containsKey(username)) {
            User user = users.get(username);
            return user.getPassword().equals(password);
        }
        return false;
    }

    private static class User {
        private String username;
        private String lastName;
        private String password;

        public User(String username, String lastName, String password) {
            this.username = username;
            this.lastName = lastName;
            this.password = password;
        }

        public String getUsername() { return username; }
        public String getLastName() { return lastName; }
        public String getPassword() { return password; }
    }

}