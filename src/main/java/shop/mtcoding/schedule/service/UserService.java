package shop.mtcoding.schedule.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.follow.Follow;
import shop.mtcoding.schedule.domain.follow.FollowRepository;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.schedule.ScheduleRepository;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserEnum;
import shop.mtcoding.schedule.domain.user.UserRepository;
import shop.mtcoding.schedule.dto.UserDetailRespDto;
import shop.mtcoding.schedule.handler.ex.CustomApiException;

/*
 * @Transactional 어노테이션이 없으면
 * DB에 요청 후 응답 받으면 db session 사라짐(OSIV=false 일 경우)
 * 서비스 레이어에서 Lazy Loading을 할 수 없게됨.
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User 회원가입(User user) {
        // 1. 동일 유저네임 존재 검사
        Optional<User> userOP = userRepository.findByUsername(user.getUsername());
        if (userOP.isPresent()) {
            throw new CustomApiException("동일한 username이 존재합니다");
        }

        // 2. 패스워드 인코딩 + DB 저장
        String encPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encPassword);
        user.setRole(UserEnum.CUSTOMER);
        User userPS = userRepository.save(user);

        // dto response
        return userPS;
    }

    public UserDetailRespDto 유저상세보기(Long detailPageUserId, Long loginUserId) {
        List<Schedule> sList = scheduleRepository.findByUserId(detailPageUserId);
        List<Todo> tList = todoRepository.findByUserId(detailPageUserId);

        List<Follow> tempFollowingList = followRepository.findFollowing(detailPageUserId);
        List<Follow> tempFollwerList = followRepository.findFollowing(detailPageUserId);
        List<User> followingUser = tempFollowingList.stream().map((f) -> f.getToUser()).collect(Collectors.toList());
        List<User> followerUser = tempFollwerList.stream().map((f) -> f.getFromUser()).collect(Collectors.toList());

        boolean isMy = true;
        if (loginUserId != detailPageUserId) {
            isMy = false;
        }

        Follow follow = followRepository.findIsFollow(loginUserId, detailPageUserId);
        boolean isFollow;
        if (follow == null) {
            isFollow = false;
        } else {
            isFollow = true;
        }

        return new UserDetailRespDto(isMy, isFollow, sList, tList, followingUser, followerUser);
    }

}
