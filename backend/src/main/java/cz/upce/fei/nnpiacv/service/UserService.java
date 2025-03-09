package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.repository.ProfileRepository;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import domain.Profile;
import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository _userRepository;
    private final ProfileRepository _profileRepository;

    public UserService(UserRepository userRepository, ProfileRepository profileRepository) {
        this._userRepository = userRepository;
        this._profileRepository = profileRepository;
        createUserWithProfile(2L, "user@example.com", "password123", "01-01-1990", "user profile description");
    }

    public List<User> findUsers() {
        return _userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return _userRepository.findById(id);
    }

    public Optional<User> findUserByEmail(String email) {
        return _userRepository.findByEmail(email);
    }

    public User createUserWithProfile(Long id, String email, String password, String birthDate, String description) {
        User user = new User(id, password, email);
        Profile profile = new Profile(1L, birthDate, description);
        profile.setUser(user);
        user.setProfile(profile);

        _userRepository.save(user);
        _profileRepository.save(profile);
        return user;
    }
}
