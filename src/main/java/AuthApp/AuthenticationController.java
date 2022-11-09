package AuthApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.regex.Pattern;

public class AuthenticationController {
    private static AuthenticationController singleInstance = null;
    private AuthenticationService authenticationService;
    private static Logger logger = LogManager.getLogger(AuthenticationController.class.getName());

    private AuthenticationController() throws IOException {
        authenticationService = AuthenticationService.getInstance();
    }

    public static AuthenticationController getInstance() throws IOException {
        if (singleInstance == null) {
            singleInstance = new AuthenticationController();
        }

        return singleInstance;
    }

    public String login(String email, String password) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Your email address is invalid!");
        }

        return this.authenticationService.login(email, password);
    }

    public boolean register(String email, String name, String password) {
        logger.info("currently registering with email: "+ email);
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address!");
        }
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name!");
        }
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Invalid password!");
        }

        return this.authenticationService.register(email, name, password);
    }

    public boolean isValidPassword(String password) {
        if(!password.matches(".*[A-Z].*") && password.length() >= 6){
        logger.error("invalid password, required 6 letters or more");
        }
        return password.matches(".*[A-Z].*") && password.length() >= 6;
    }

    public boolean isValidName(String Name) {
        if(!Name.matches("^[ A-Za-z]+$")) {
            logger.error("invalid Name, must include english letters / capital letters only!");
        }
        return Name.matches("^[ A-Za-z]+$");
    }

    public static boolean isValidEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";

        if (!Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches()) {
            logger.error("invalid email, must include english letters / capital letters them @, then the website where the mail is located");
        }
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public void updateTokenEmailKey(String oldEmail, String newEmail) {
        this.authenticationService.updateTokenEmailKey(oldEmail, newEmail);
    }
}