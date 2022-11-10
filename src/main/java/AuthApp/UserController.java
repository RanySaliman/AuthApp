package AuthApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationController authenticationController;
    private static Logger logger = LogManager.getLogger(AuthenticationController.class.getName());


    private UserController(){}


    public boolean updateUserName(String email, String name, String token) throws IOException {

        if (!authenticationController.isValidName(name)) {
            throw new IllegalArgumentException(String.format("%s is invalid name!", name));
        }

        return userService.updateUserName(email, name, token);
    }

    public boolean updateUserEmail(String email, String newEmail, String token) throws IOException {

        if (!authenticationController.isValidEmail(newEmail)) {
            logger.error("invalid email - " + email);
            throw new IllegalArgumentException(String.format("%s is invalid email!", newEmail));
        }

        boolean success = userService.updateUserEmail(email, newEmail, token);
        authenticationController.updateTokenEmailKey(email, newEmail);

        return success;
    }

    public boolean updateUserPassword(String email, String password, String token) throws IOException {

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
