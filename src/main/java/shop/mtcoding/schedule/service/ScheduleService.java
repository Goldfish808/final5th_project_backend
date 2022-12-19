package shop.mtcoding.schedule.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import shop.mtcoding.schedule.dto.HomeRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;
    private final FollowRepository followRepository;

    public HomeRespDto findHome(Long userId, String startAt) {

        System.out.println("디버그 : startAt : " + startAt);
        List<Schedule> sList = scheduleRepository.findByStartAt(userId, startAt);
        List<Todo> tList = todoRepository.findByUserId(userId);

        List<Follow> fList = followRepository.findFollowing(userId);

        List<User> toUsers = fList.stream().map((f) -> f.getToUser()).collect(Collectors.toList());

        return new HomeRespDto(sList, tList, toUsers);
    }

    public List<Schedule> findCalender(Long userId, String startAt) {
        List<Schedule> sList = scheduleRepository.findByStartAt(userId, startAt);
        return sList;
    }

}
