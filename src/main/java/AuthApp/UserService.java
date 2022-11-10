package AuthApp;

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


    private UserService() throws IOException {
        userRepository = AuthApp.UserRepository.getInstance();
    }

    public boolean updateUserName(String email, String name, String token) throws IOException {
        validateToken(email, token);
        Optional<User> user = userRepository.getUserByEmail(email);

        if (user.isPresent()) {
            user.get().setName(name);
            return userRepository.updatedUser(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address: %s does not exist", email));
        }
    }

    public boolean updateUserEmail(String email, String newEmail, String token) throws IOException {
        validateToken(email, token);

        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            user.get().setEmail(newEmail);
            return userRepository.updatedUser(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

    public boolean updateUserPassword(String email, String password, String token) throws IOException {
        validateToken(email, token);

        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            user.get().setPassword(password);
            return userRepository.updatedUser(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

    public boolean deleteUser(String email, String token) throws IOException {
        validateToken(email, token);

        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            return userRepository.deleteUser(user.get());
        } else {
            throw new IllegalArgumentException(String.format("Email address %s does not match any user", email));
        }
    }

    private void validateToken(String email, String token) throws IOException {
        if (!authenticationService.isValidToken(email, token)) {
            throw new AccessDeniedException(String.format("User with email address: %s is not logged in!", email));
        }
    }
}
