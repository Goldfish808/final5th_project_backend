package shop.mtcoding.schedule.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.config.auth.LoginUser;
import shop.mtcoding.schedule.config.jwt.JwtProcess;
import shop.mtcoding.schedule.config.jwt.JwtVO;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserRepository;
import shop.mtcoding.schedule.dto.response.ResponseDto;
import shop.mtcoding.schedule.handler.ex.CustomApiException;
import shop.mtcoding.schedule.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/jwtToken")
    public ResponseEntity<?> jwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("authorization");
        if (jwtToken == null) {
            throw new CustomApiException("토큰이 헤더에 없습니다.");
        }
        jwtToken = jwtToken.replace(JwtVO.TOKEN_PREFIX, "");
        LoginUser loginUser = JwtProcess.verify(jwtToken);
        User userEntity = userRepository.findById(loginUser.getUser().getId())
                .orElseThrow(() -> new CustomApiException("토큰 검증 실패"));

        return new ResponseEntity<>(
                new ResponseDto<>(1, "성공", userEntity),
                HttpStatus.OK);

    }

    @GetMapping("/s/user/{userId}")
    public ResponseEntity<?> userDetail(
            @PathVariable Long userId,
            @AuthenticationPrincipal LoginUser loginUser) {

        return new ResponseEntity<>(
                new ResponseDto<>(1, "성공", userService.유저상세보기(userId, loginUser.getUser().getId())),
                HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user) {
        User userPS = userService.회원가입(user);
        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", userPS), HttpStatus.OK);
    }

}
