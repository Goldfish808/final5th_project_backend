package shop.mtcoding.schedule.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.follow.Follow;
import shop.mtcoding.schedule.domain.follow.FollowRepository;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.schedule.ScheduleRepository;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.dto.response.ResponseDto;

@RequiredArgsConstructor
@RestController
public class IndexController {
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;
    private final FollowRepository followRepository;

    @GetMapping("/test/following")
    public ResponseEntity<?> fList() {
        List<Follow> followings = followRepository.findFollowing(1L);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", followings), HttpStatus.OK);
    }

    @GetMapping("/test/schedule/time")
    public ResponseEntity<?> sList() {
        List<Schedule> sList = scheduleRepository.findByStartAt(1L, "2022-12-19");
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", sList), HttpStatus.OK);
    }

    @GetMapping("/test/todo")
    public ResponseEntity<?> tTodo() {
        List<Todo> todoList = todoRepository.findByUserId(1L);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", todoList), HttpStatus.OK);
    }
}
