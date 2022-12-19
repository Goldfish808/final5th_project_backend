package shop.mtcoding.schedule.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserRepository;

@Configuration
public class DevInit extends DummyObject {

    @Bean
    public CommandLineRunner dummyInit(UserRepository userRepository) {
        return (args) -> {
            User ssar = userRepository.save(newUser("ssar"));
        };
    }
}
