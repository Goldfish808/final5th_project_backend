package shop.mtcoding.schedule.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.follow.FollowRepository;
import shop.mtcoding.schedule.dto.response.ResponseDto;

@RequiredArgsConstructor
@RestController
public class FollowController {
    private final FollowRepository followRepository;

    @GetMapping("/s/follow")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", followRepository.findAll()), HttpStatus.OK);
    }
}
