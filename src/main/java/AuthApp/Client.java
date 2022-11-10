//package AuthApp;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.io.IOException;
//import java.nio.file.AccessDeniedException;
//
//public class Client {
//    private final AuthenticationController authenticationController;
//    private final UserController userController;
//    private String token;
//    private static Logger logger = LogManager.getLogger(Client.class.getName());
//
//
//    public Client() throws IOException {
//        this.userController = UserController.getInstance();
//    }
//
//    public boolean login(String email, String password) {
//        this.token = authenticationController.login(email, password);
//        logger.info("trying to log in to " + email);
//        boolean success = token != null;
//        return success;
//    }
//
//    public boolean register(String email, String name, String password) {
//        logger.debug("trying to register with" + email );
//        return authenticationController.register(email, name, password);
//    }
//
//    public boolean updateUserName(String email, String name) throws IOException {
//        logger.info("trying to update name for " + email);
//        if (this.token == null) {
//            logger.error(email + " email is not logged in");
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        }
//
//        return this.userController.updateUserName(email, name, this.token);
//    }
//
//    public boolean updateUserEmail(String email, String newEmail) throws IOException {
//        logger.info("trying to update email for " + email);
//        if (this.token == null) {
//            logger.error(email + " email is not logged in");
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        }
//
//        return this.userController.updateUserEmail(email, newEmail, this.token);
//    }
//
//    public boolean updateUserPassword(String email, String password) throws IOException {
//        logger.info("trying to update password for " + email);
//        if (this.token == null) {
//            logger.error(email + " email is not logged in");
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        }
//
//        return userController.updateUserPassword(email, password, token);
//    }
//
//    public boolean deleteUser(String email) throws IOException {
//        logger.info("trying to delete " + email);
//        if (this.token == null) {
//            logger.error(email + " email is not logged in");
//            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
//        }
//
//        return userController.deleteUser(email, this.token);
//    }
//}
