package shop.mtcoding.schedule.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.dto.ResponseDto;
import shop.mtcoding.schedule.service.TodoService;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoRepository todoRepository;
    private final TodoService todoService;

    @GetMapping("/s/todo")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", todoRepository.findAll()), HttpStatus.OK);
    }

    @PostMapping("/s/todo")
    public ResponseEntity<?> save(@RequestBody Todo todo) {
        Todo todoPS = todoRepository.save(todo);
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", todoPS), HttpStatus.OK);
    }

}
