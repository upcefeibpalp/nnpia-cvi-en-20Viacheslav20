package cz.upce.fei.nnpiacv.service;

import domain.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final Map<Long, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put(1L, new User(1L, "password123", "user1@example.com"));
        users.put(2L, new User(2L, "securePass", "user2@example.com"));
        LOGGER.info("User initialization completed: {}", users);
    }

    public User findUserById(Long id) {
        User user = users.get(id);
        if (user != null) {
            LOGGER.info("User found: {}", user);
        } else {
            LOGGER.warn("User with ID {} not found.", id);
        }
        return user;
    }

    public List<User> findUsers() {
        LOGGER.info("Fetching all users, total count: {}", users.size());
        return users.values().stream().collect(Collectors.toList());
    }
}
