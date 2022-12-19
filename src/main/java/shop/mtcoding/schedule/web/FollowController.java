package shop.mtcoding.schedule.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.config.auth.LoginUser;
import shop.mtcoding.schedule.domain.follow.Follow;
import shop.mtcoding.schedule.domain.follow.FollowRepository;
import shop.mtcoding.schedule.dto.response.ResponseDto;
import shop.mtcoding.schedule.service.FollowService;

@RequiredArgsConstructor
@RestController
public class FollowController {
    private final FollowRepository followRepository;
    private final FollowService followService;

    @PostMapping("/s/follow/{toUserId}")
    public ResponseEntity<?> follow(
            @PathVariable Long toUserId,
            @AuthenticationPrincipal LoginUser loginUser) {
        Follow followPS = followService.팔로우(loginUser.getUser().getId(), toUserId);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", followPS), HttpStatus.OK);
    }

    @PutMapping("/s/unFollow/{toUserId}")
    public ResponseEntity<?> unFollow(@PathVariable Long toUserId,
            @AuthenticationPrincipal LoginUser loginUser) {
        Follow followPS = followService.팔로우취소(loginUser.getUser().getId(), toUserId);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", followPS), HttpStatus.OK);
    }

    @GetMapping("/s/follow")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", followRepository.findAll()), HttpStatus.OK);
    }
}
