package org.example;


import java.util.*;
import java.util.regex.Pattern;

public class Login {

    public static Map<String, User> users = new HashMap<>();

    public static Map setDB(String username,String phone, String password){
        users.put(username , new User(username, phone, password));
        return users;
    }

    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
                Pattern.compile("[A-Z]").matcher(password).find() &&
                Pattern.compile("[0-9]").matcher(password).find() &&
                Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    public static boolean checkCellPhoneNumber(String phoneNumber) {
        // Assuming international format includes '+' and correct length is 13 (e.g., +123456789012)
        String regex = "^\\+27[1-9][0-9]{8}$";
        if (Pattern.matches(regex, phoneNumber)) {
            return true;
        } else {
            return false;
        }

    }

    public static ArrayList registerUser(boolean username, boolean password, boolean phoneNumber) {

        ArrayList<String> messages = new ArrayList<String>();

        if (username) {
            messages.add("Username successfully captured.");
        } 
        if (!username) {
            messages.add("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        }
        if (password) {
            messages.add("Password successfully captured.");
        }
        if (!password) {
            messages.add("Password incorrectly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        }
        if (phoneNumber) {
            messages.add("Cell number successfully captured.");
        }
        else {
            messages.add("Cell number incorrectly formatted or does not contain international code;please correct the number and try again.");

        }

        return messages;
    }

    public static String returnLoginStatus(boolean loginSuccess, String username) {
        User user = users.get(username);
        return loginSuccess ? "Welcome " + user.getUsername() + " " + user.getLastName() + ", it is great to see you again." : "Username or password incorrect, please try again.";
    }
    public static void main(String[] args) {
        // Sample user data (in a real app, this would be in a database)

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone number(only South African): ");
        String phone = scanner.nextLine();

        boolean checkusername = checkUserName(username);
        boolean checkpassword = checkPasswordComplexity(password);
        boolean checkphone = checkCellPhoneNumber(phone);

        if (checkusername && checkpassword && checkphone){
            users.put(username , new User(username, phone, password));
        }

        System.out.println(checkusername ? "Username successfully captured." : "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
        System.out.println(checkpassword ? "Password successfully captured." : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
        System.out.println(checkpassword ? "Cell number successfully captured." : "Cell number incorrectly formatted or does not contain international code.");


        System.out.print("Enter username: ");
        String username1 = scanner.nextLine();
        System.out.print("Enter password: ");
        String password1 = scanner.nextLine();



        if (loginUser(users, username1, password1)) {
            System.out.println(returnLoginStatus(true, username1));
        } else {
            System.out.println(returnLoginStatus(false, username1));
        }
    }

    public static boolean loginUser(Map<String, User> users, String username, String password) {
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