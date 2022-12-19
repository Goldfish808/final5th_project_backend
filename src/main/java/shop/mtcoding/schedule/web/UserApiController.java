package shop.mtcoding.schedule.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.dto.ResponseDto;
import shop.mtcoding.schedule.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        User userPS = userService.회원가입(user);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", userPS), HttpStatus.CREATED);
    }

}
