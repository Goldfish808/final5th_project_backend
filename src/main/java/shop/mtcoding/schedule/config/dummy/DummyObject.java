package shop.mtcoding.schedule.config.dummy;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserEnum;

public class DummyObject {
    protected User newUser(String username) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        User user = User.builder()
                .username(username)
                .password(encPassword)
                .email(username + "@nate.com")
                .role(UserEnum.CUSTOMER)
                .build();
        return user;
    }

}
