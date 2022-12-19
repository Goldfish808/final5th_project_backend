package shop.mtcoding.schedule.config.dummy;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import shop.mtcoding.schedule.domain.category.Category;
import shop.mtcoding.schedule.domain.category.CategoryEnum;
import shop.mtcoding.schedule.domain.follow.Follow;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserEnum;

public class DummyObject {

    protected Follow newFollow(User fromUser, User toUser) {
        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .isActive(true)
                .build();
        return follow;
    }

    protected Todo newTodo(String title, User user) {
        Todo todo = Todo.builder()
                .title(title)
                .isFinished(false)
                .user(user)
                .build();
        return todo;
    }

    protected Schedule newSchedule(int hour, User user, Category category) {
        LocalDateTime startAt = LocalDateTime.now().minusHours(hour);
        LocalDateTime finishAt = LocalDateTime.now().plusHours(hour);
        Schedule schedule = Schedule.builder()
                .title("밥먹는 시간 정하기")
                .note("오늘은 밥먹어야 함")
                .address("부산 진구 중앙대로")
                .imgUrl("1.png")
                .category(category)
                .startAt(startAt)
                .finishAt(finishAt)
                .user(user)
                .build();
        return schedule;
    }

    protected Category newCategory(String name, CategoryEnum color) {
        Category category = Category.builder()
                .color(color)
                .name(name)
                .build();
        return category;
    }

    protected User newUser(String username) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encPassword = passwordEncoder.encode("1234");
        User user = User.builder()
                .username(username)
                .password(encPassword)
                .email(username + "@nate.com")
                .role(UserEnum.CUSTOMER)
                .build();
        return user;
    }

}
