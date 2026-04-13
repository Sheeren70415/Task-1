import org.example.Login;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    private Login simpleLogin = new Login();


    @Test
    void goodUsername() {

        String username = "kyl_1";
        String password = "ch&&sec@ke99!";
        String phone = "+27838968976";

        Map userDB = Login.setDB(username, phone, password);
        boolean loginCheck = simpleLogin.loginUser(userDB, username, password);
        assertEquals("Welcome " + username + " " + phone + "," + " it is great to see you again.", simpleLogin.returnLoginStatus(loginCheck, username));
    }

    @Test
    void badUsername() {

        String username = "kyle!!!!!!!";

        boolean verifyUsername = simpleLogin.checkUserName(username);

        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                simpleLogin.registerUser(verifyUsername, true, true).get(0));
    }

    @Test
    void validatePassword() {

        String password = "Ch&&sec@ke99!";
        String wrongPassword = "password";

        boolean verifyPassword = simpleLogin.checkPasswordComplexity(password);
        boolean verifyWrongPassword = simpleLogin.checkPasswordComplexity(wrongPassword);

        assertEquals("Password successfully captured.",
                simpleLogin.registerUser(true, verifyPassword, true).get(1));
        assertEquals("Password incorrectly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                simpleLogin.registerUser(true, verifyWrongPassword, true).get(1));

    }

    @Test
    void validatePhoneNumber() {

                                                                                                String phone = "+27838968976";
        String password = "ch&&sec@ke99!";
        String wrongPhone = "08966553";

        boolean verifyPhone = simpleLogin.checkCellPhoneNumber(phone);
        boolean verifyWrongPhone = simpleLogin.checkCellPhoneNumber(wrongPhone);

        assertEquals("Cell number successfully captured.",
                simpleLogin.registerUser(true, verifyPhone, true).get(2));
        assertEquals("Cell number incorrectly formatted or does not contain international code;please correct the number and try again.",
                simpleLogin.registerUser(true, true, verifyWrongPhone).get(2));

    }

    @Test
    void validateLogin() {
        String username = "kyl_1";
        String usernameWrong = "kyl_1!!!!!!!!!";

        String password = "ch&&sec@ke99!";
        String phone = "+27838968976";

        Map userDB = Login.setDB(username, phone, password);
        boolean loginCheck = simpleLogin.loginUser(userDB, username, password);
        assertTrue(loginCheck);
        boolean loginCheck1 = simpleLogin.loginUser(userDB, usernameWrong, password);
        assertFalse(loginCheck1);
    }


    @Test
    void ValidateUsername() {

        String username = "kyl_1";
        String wrongUsername = "kyl!!!!!!";

        boolean checkUserName = simpleLogin.checkUserName(username);
        assertTrue(checkUserName);
        boolean checkWrongUserName = simpleLogin.checkUserName(wrongUsername);
        assertFalse(checkWrongUserName);
    }

    @Test
    void testPasswordComplexity() {
        String validPassword = "ch&&sec@keH99!";
        String invalidPassword = "password";

        boolean checkValidPassword = simpleLogin.checkPasswordComplexity(validPassword);
        assertTrue(checkValidPassword);
        boolean checkInvalidPassword = simpleLogin.checkPasswordComplexity(invalidPassword);
        assertFalse(checkInvalidPassword);
    }

    @Test
    void testPhoneNumber() {

        String validPhone = "+27838968976";
        String invalidPhone = "08966553";

        boolean checkCellPhoneNumber = simpleLogin.checkCellPhoneNumber(validPhone);
        assertTrue(checkCellPhoneNumber);
        boolean checkCellPhoneNumber1 = simpleLogin.checkCellPhoneNumber(invalidPhone);
        assertFalse(checkCellPhoneNumber1);
    }





}


