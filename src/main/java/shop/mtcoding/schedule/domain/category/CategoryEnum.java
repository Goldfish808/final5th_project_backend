package shop.mtcoding.schedule.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {
    PURPLE("일반", 1L), GREEN("업무", 2L), YELLOW("친구", 3L);

    private String name;
    private Long id;
}
