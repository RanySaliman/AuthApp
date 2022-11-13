package AuthApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationService authenticationService;

    private static Logger logger = LogManager.getLogger(AuthenticationController.class.getName());


    private UserController() {
    }

    @RequestMapping(value = "/updateEmail", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUserEmail(@RequestBody User user, @RequestHeader String token) throws IOException {
        try {

            return ResponseEntity.ok(userService.updateUserEmail(user.getEmail(), token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }

    }

    @RequestMapping(value = "/updateUserName", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUserName(@RequestBody User user, @RequestHeader String token) throws IOException {
        try {

            return ResponseEntity.ok(userService.updateUserName(user.getName(), token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }

    }
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PATCH)
    public ResponseEntity<String> updateUserPassword(@RequestBody User user, @RequestHeader String token) throws IOException {
        try {

            return ResponseEntity.ok(userService.updateUserPassword(user.getPassword(), token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.toString());
        }

    }

//    public boolean updateUserName(String email, String name, String token) throws IOException {
//
//        if (!authenticationController.isValidName(name)) {
//            throw new IllegalArgumentException(String.format("%s is invalid name!", name));
//        }
//
//        return userService.updateUserName(name, token);
//    }

//    public boolean updateUserEmail(String email, String newEmail, String token) throws IOException {
//
//        if (!authenticationController.isValidEmail(newEmail)) {
//            logger.error("invalid email - " + email);
//            throw new IllegalArgumentException(String.format("%s is invalid email!", newEmail));
//        }
//
//        boolean success = userService.updateUserEmail(newEmail, token);
//        authenticationController.updateTokenEmailKey(email, newEmail);
//
//        return success;
//    }

//    public boolean updateUserPassword(String email, String password, String token) throws IOException {
//
//        if (!authenticationController.isValidPassword(password)) {
//            logger.error("invalid password - " + password);
//            throw new IllegalArgumentException(String.format("%s is invalid password!", password));
//        }
//
//        return userService.updateUserPassword(email, password, token);
//    }

    public boolean deleteUser(String email, String token) throws IOException {
        return userService.deleteUser(email, token);
    }
}
