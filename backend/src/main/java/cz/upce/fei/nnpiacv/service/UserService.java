package cz.upce.fei.nnpiacv.service;

import cz.upce.fei.nnpiacv.exception.UserAlreadyExistsException;
import cz.upce.fei.nnpiacv.exception.UserNotFoundException;
import cz.upce.fei.nnpiacv.repository.UserRepository;
import cz.upce.fei.nnpiacv.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private final UserRepository _userRepository;

    public UserService(UserRepository userRepository) {
        this._userRepository = userRepository;
    }

    public List<User> getUsers() {
        return _userRepository.findAll();
    }

    public User getUserById(Long id) {
        return _userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User getUserByEmail(String email) {
        return _userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public User addUser(User user) {
        if (_userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        return _userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = _userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setName(userDetails.getName());

        return _userRepository.save(user);
    }

    public User setUserActiveStatus(Long id, boolean isActive) {
        User user = getUserById(id);
        user.setActive(isActive);
        return _userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = _userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        _userRepository.delete(user);
    }
}
