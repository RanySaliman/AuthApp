package AuthApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class UserController {
    private static UserController singleInstance = null;
    private UserService userService;
    private static Logger logger = LogManager.getLogger(AuthenticationController.class.getName());


    private UserController() throws IOException {
        userService = UserService.getInstance();
    }

    public static UserController getInstance() throws IOException {
        if (singleInstance == null) {
            singleInstance = new UserController();
        }

        return singleInstance;
    }

    public boolean updateUserName(String email, String name, String token) throws IOException {
        AuthenticationController authenticationController = AuthenticationController.getInstance();

        if (!authenticationController.isValidName(name)) {
            throw new IllegalArgumentException(String.format("%s is invalid name!", name));
        }

        return userService.updateUserName(email, name, token);
    }

    public boolean updateUserEmail(String email, String newEmail, String token) throws IOException {
        AuthenticationController authenticationController = AuthenticationController.getInstance();

        if (!authenticationController.isValidEmail(newEmail)) {
            logger.error("invalid email - " + email);
            throw new IllegalArgumentException(String.format("%s is invalid email!", newEmail));
        }

        boolean success = userService.updateUserEmail(email, newEmail, token);
        authenticationController.updateTokenEmailKey(email, newEmail);

        return success;
    }

    public boolean updateUserPassword(String email, String password, String token) throws IOException {
        AuthenticationController authenticationController = AuthenticationController.getInstance();

        if (!authenticationController.isValidPassword(password)) {
            logger.error("invalid password - " + password);
            throw new IllegalArgumentException(String.format("%s is invalid password!", password));
        }

        return userService.updateUserPassword(email, password, token);
    }

    public boolean deleteUser(String email, String token) throws IOException {
        return userService.deleteUser(email, token);
    }
}
