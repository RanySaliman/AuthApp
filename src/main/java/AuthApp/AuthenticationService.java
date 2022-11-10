package AuthApp;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
class AuthenticationService {

    private UserRepository userRepository;
    private Map<String, String> usersTokens;
    private static final int TOKEN_LENGTH = 10;


    private AuthenticationService() {
        usersTokens = new HashMap<>();
    }


    public String login(String email, String password) {
        String token = isValidCredentials(email, password) ? generateToken() : null;

        if (token != null) {
            this.usersTokens.put(email, token);
        }

        return token;
    }

    public boolean register(String email, String name, String password) {
        if (isExistingEmail(email)) {
            throw new IllegalArgumentException("This email address already exists!");
        }

        User user = User.createUser(email, name, password);
        this.userRepository.addUser(user);

        return true;
    }

    public boolean isValidToken(String email, String token) {
        return this.usersTokens.get(email).compareTo(token) == 0;
    }

    public void updateTokenEmailKey(String oldEmail, String newEmail) {
        this.usersTokens.put(newEmail, this.usersTokens.get(oldEmail));
        this.usersTokens.remove(oldEmail);
    }

    private boolean isValidCredentials(String email, String password) {
        Optional<User> user = userRepository.getUserByEmail(email);

        if (user.isPresent()) {
            return user.get().getPassword().compareTo(password) == 0;
        }

        return false;
    }

    private boolean isExistingEmail(String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        return user.isPresent();
    }

    private static String generateToken() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);

        for (int i = 0; i < TOKEN_LENGTH; ++i) {
            int index = (int) ((double) AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}
