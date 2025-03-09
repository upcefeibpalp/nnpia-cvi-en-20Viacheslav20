package cz.upce.fei.nnpiacv.component;

import cz.upce.fei.nnpiacv.repository.UserRepository;
import domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        User adminUser = new User(1L, "admin123", "admin@example.com");
//
//        userRepository.save(adminUser);
//
//        LOGGER.info("Admin user created: {}", adminUser);
    }
}
