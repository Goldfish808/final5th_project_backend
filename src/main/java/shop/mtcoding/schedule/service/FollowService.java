package shop.mtcoding.schedule.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.category.Category;
import shop.mtcoding.schedule.domain.category.CategoryEnum;
import shop.mtcoding.schedule.domain.category.CategoryRepository;
import shop.mtcoding.schedule.domain.follow.Follow;
import shop.mtcoding.schedule.domain.follow.FollowRepository;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.schedule.ScheduleRepository;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserRepository;
import shop.mtcoding.schedule.dto.request.ScheduleReqDto;
import shop.mtcoding.schedule.dto.response.HomeRespDto;
import shop.mtcoding.schedule.handler.ex.CustomApiException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FollowService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;
    private final FollowRepository followRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Follow 팔로우(Long fromUserId, Long toUserId) {
        Follow followOP = followRepository.findByFollowCheck(fromUserId, toUserId);
        if (followOP == null) {
            System.out.println("디버그 : insert");
            User fromUser = userRepository.findById(fromUserId).orElseThrow(
                    () -> new CustomApiException("해당 유저를 찾을 수 없습니다"));
            User toUser = userRepository.findById(toUserId).orElseThrow(
                    () -> new CustomApiException("해당 유저를 찾을 수 없습니다"));
            Follow follow = Follow.builder()
                    .fromUser(fromUser)
                    .toUser(toUser)
                    .isActive(true)
                    .build();
            Follow followPS = followRepository.save(follow);
            return followPS;
        } else {
            if (followOP.getIsActive()) {
                throw new CustomApiException("이미 팔로우를 한 상태입니다");
            }
            System.out.println("디버그 : update");
            followOP.setIsActive(true);
            return followOP;
        }
    }

    @Transactional
    public Follow 팔로우취소(Long fromUserId, Long toUserId) {
        Follow followOP = followRepository.findByUnFollowCheck(fromUserId, toUserId);
        if (followOP == null) {
            throw new CustomApiException("해당 팔로우를 찾을 수 없어서 팔로우 취소를 할 수 없습니다");
        } else {
            followOP.setIsActive(false);
            return followOP;
        }
    }

}
