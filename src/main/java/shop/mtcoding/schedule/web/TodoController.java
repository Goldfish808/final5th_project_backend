package shop.mtcoding.schedule.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.dto.ResponseDto;

@RequiredArgsConstructor
@RestController
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping("/s/todo")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", todoRepository.findAll()), HttpStatus.OK);
    }
}
