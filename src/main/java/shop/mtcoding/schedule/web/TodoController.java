package shop.mtcoding.schedule.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.config.auth.LoginUser;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.dto.response.ResponseDto;
import shop.mtcoding.schedule.service.TodoService;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoRepository todoRepository;
    private final TodoService todoService;

    @PutMapping("/s/todo/{todoId}/finish")
    public ResponseEntity<?> todoFinish(@PathVariable Long todoId, @AuthenticationPrincipal LoginUser loginUser) {
        Todo todoPS = todoService.투두종료(todoId, loginUser.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", todoPS), HttpStatus.OK);
    }

    @GetMapping("/s/todo")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", todoRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/s/todo")
    public ResponseEntity<?> save(@RequestBody Todo todo, @AuthenticationPrincipal LoginUser loginUser) {
        System.out.println("디버그 : todo titie : " + todo.getTitle());
        Todo todoPS = todoService.투두작성(todo, loginUser.getUser().getId());
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", todoPS), HttpStatus.OK);
    }

}
