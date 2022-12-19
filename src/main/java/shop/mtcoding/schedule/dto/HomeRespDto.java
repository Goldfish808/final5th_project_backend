package shop.mtcoding.schedule.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.user.User;

@Setter
@Getter
public class HomeRespDto {
    private List<Schedule> schedules = new ArrayList<>();
    private List<Todo> todos = new ArrayList<>();
    private List<User> followingUser = new ArrayList<>();

    public HomeRespDto(List<Schedule> schedules, List<Todo> todos, List<User> followingUser) {
        this.schedules = schedules;
        this.todos = todos;
        this.followingUser = followingUser;
    }
}