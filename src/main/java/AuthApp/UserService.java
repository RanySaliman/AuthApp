package AuthApp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    AuthenticationService authenticationService;

    private static Logger logger = LogManager.getLogger(AuthenticationController.class.getName());


    private UserService() {
    }

    public String updateUserName(String name, String token) throws IOException {
        String email = authenticationService.returnEmailFromToken(token);

        if (token == null) {
            logger.error("Wrong token");
            throw new IllegalArgumentException("Wrong token");
        }
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            user.get().setName(name);
            if (userRepository.updatedUser(user.get())) {
                return "Username has been updated to " + name;
            }
        }
        return "Username was already " + name;
    }

    public String updateUserEmail(String newEmail, String token) throws IOException {
        String email = authenticationService.returnEmailFromToken(token);
        if (token == null) {
            logger.error("Wrong token");
            throw new IllegalArgumentException("Wrong token");
        }
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            user.get().setEmail(newEmail);
            if (userRepository.updatedUser(user.get()))
                return "Email has been updated to "+ newEmail;
        }

        return "Email was already " + newEmail;
    }

    public String updateUserPassword(String password, String token) throws IOException {
        String email = authenticationService.returnEmailFromToken(token);
        if (token == null) {
            logger.error("Wrong token");
            throw new IllegalArgumentException("Wrong token");
        }
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            user.get().setPassword(password);
            if(userRepository.updatedUser(user.get()))
                return  "Password has been updated to " +password;
        }
        return "Password was already " + password;

    }

    public boolean deleteUser(String email, String token) throws IOException {
        String emailbla = authenticationService.returnEmailFromToken(token);
        Optional<User> user = userRepository.getUserByEmail(emailbla);
        if (user.isPresent()) {
            return userRepository.deleteUser(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

//    private void validateToken(String email, String token) throws IOException {
//        if (!authenticationService.isValidToken(email, token)) {
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        }
//    }

}
