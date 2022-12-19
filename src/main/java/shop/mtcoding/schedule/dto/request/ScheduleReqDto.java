package shop.mtcoding.schedule.dto.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.schedule.domain.category.Category;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.user.User;

@Setter
@Getter
public class ScheduleReqDto {
    private String title;
    private String note;
    private String address;
    private String categoryName;
    private LocalDateTime startAt; // 날짜+시간 받기
    private LocalDateTime finishAt; // 날짜+시간 받기

    public Schedule toEntity(String imgUrl, Category category, User user) {
        Schedule schedule = Schedule.builder()
                .title(title)
                .note(note)
                .imgUrl(imgUrl)
                .address(address)
                .category(category)
                .user(user)
                .startAt(startAt)
                .finishAt(finishAt)
                .build();
        return schedule;
    }
}
