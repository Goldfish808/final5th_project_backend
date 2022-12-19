package shop.mtcoding.schedule.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.follow.FollowRepository;
import shop.mtcoding.schedule.domain.schedule.ScheduleRepository;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserRepository;
import shop.mtcoding.schedule.handler.ex.CustomApiException;

/*
 * @Transactional 어노테이션이 없으면
 * DB에 요청 후 응답 받으면 db session 사라짐(OSIV=false 일 경우)
 * 서비스 레이어에서 Lazy Loading을 할 수 없게됨.
 */
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TodoService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Todo 투두작성(Todo todo, Long userId) {
        User userPS = userRepository.findById(userId).orElseThrow(() -> new CustomApiException("유저를 찾을 수 없습니다"));
        todo.setUser(userPS);
        todo.setIsFinished(false);

        Todo todoPS = todoRepository.save(todo);
        return todoPS;
    }

    @Transactional
    public Todo 투두종료(Long todoId, Long userId) {
        Todo todoPS = todoRepository.findById(todoId).orElseThrow(
                () -> new CustomApiException("투두를 찾을 수 없습니다"));

        if (todoPS.getUser().getId() != userId) {
            throw new CustomApiException("투두를 종료할 권한이 없습니다");
        }

        todoPS.setIsFinished(true);
        return todoPS;
    }

}
