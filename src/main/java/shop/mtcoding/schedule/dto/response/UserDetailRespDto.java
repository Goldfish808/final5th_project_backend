package shop.mtcoding.schedule.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.user.User;

@Setter
@Getter
public class UserDetailRespDto {
    private Boolean isMy; // 내페이지
    private Boolean isFollow; // isMy = false 일때만 의미가 있음.
    private List<Schedule> schedules = new ArrayList<>();
    private List<Todo> todos = new ArrayList<>();
    private List<User> followingUser = new ArrayList<>();
    private List<User> followerUser = new ArrayList<>();

    public UserDetailRespDto(Boolean isMy, Boolean isFollow, List<Schedule> schedules, List<Todo> todos,
            List<User> followingUser, List<User> followerUser) {
        this.isMy = isMy;
        this.isFollow = isFollow;
        this.schedules = schedules;
        this.todos = todos;
        this.followingUser = followingUser;
        this.followerUser = followerUser;
    }

}