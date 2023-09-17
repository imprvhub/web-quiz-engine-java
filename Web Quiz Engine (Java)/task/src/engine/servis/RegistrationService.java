// Web Quiz Engine (Java) -
// Graduate Project Completed By Iván Luna, September 17, 2023. -
// For Hyperskill (Jet Brains Academy). Course: Spring Security for Java Backend Developers.

package engine.servis;

import engine.exceptions.UserExistException;
import engine.model.AppUser;
import engine.model.RegistrationRequest;
import engine.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationRequest request) {
        if (userRepository.findAUserByEmail(request.email()).isPresent()) {
            throw new UserExistException();
        }

        AppUser user = new AppUser(
                request.email(),
                request.email(),
                passwordEncoder.encode(request.password()),
                "ROLE_USER"
        );

        userRepository.save(user);

    }
}