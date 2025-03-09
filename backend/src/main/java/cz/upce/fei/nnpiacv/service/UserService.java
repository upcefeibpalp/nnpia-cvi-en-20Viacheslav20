package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.repository.UserRepository;
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

    public UserService(UserRepository userRepository) {
        this._userRepository = userRepository;
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

    public User addUser(User user) {
        return _userRepository.save(user);
    }
}
