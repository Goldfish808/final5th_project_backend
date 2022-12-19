package shop.mtcoding.schedule.config.dummy;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import shop.mtcoding.schedule.domain.category.Category;
import shop.mtcoding.schedule.domain.category.CategoryEnum;
import shop.mtcoding.schedule.domain.category.CategoryRepository;
import shop.mtcoding.schedule.domain.follow.Follow;
import shop.mtcoding.schedule.domain.follow.FollowRepository;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.schedule.ScheduleRepository;
import shop.mtcoding.schedule.domain.todo.Todo;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.domain.user.UserRepository;

@Configuration
public class DevInit extends DummyObject {

    @Bean
    public CommandLineRunner dummyInit(
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ScheduleRepository scheduleRepository,
            TodoRepository todoRepository,
            FollowRepository followRepository) {
        return (args) -> {
            User ssar = userRepository.save(newUser("ssar"));
            User cos = userRepository.save(newUser("cos"));
            User love = userRepository.save(newUser("love"));
            User haha = userRepository.save(newUser("haha"));

            Category c1 = newCategory("업무", CategoryEnum.PURPLE);
            Category c2 = newCategory("일반", CategoryEnum.YELLOW);
            Category c3 = newCategory("놀이", CategoryEnum.ORANGE);
            categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

            Schedule s1 = newSchedule(1, ssar, c1);
            Schedule s2 = newSchedule(2, ssar, c1);
            Schedule s3 = newSchedule(3, ssar, c2);
            scheduleRepository.saveAll(Arrays.asList(s1, s2, s3));

            Todo t1 = newTodo("밥먹기", ssar);
            Todo t2 = newTodo("라면먹기", ssar);
            Todo t3 = newTodo("운동하기", ssar);
            Todo t4 = newTodo("숙제하기", ssar);
            Todo t5 = newTodo("양치하기", ssar);
            todoRepository.saveAll(Arrays.asList(t1, t2, t3, t4, t5));

            Follow f1 = newFollow(ssar, cos);
            Follow f2 = newFollow(ssar, love);
            Follow f3 = newFollow(ssar, haha);
            Follow f4 = newFollow(cos, ssar);
            Follow f5 = newFollow(cos, love);
            Follow f6 = newFollow(cos, haha);
            Follow f7 = newFollow(love, ssar);
            Follow f8 = newFollow(haha, ssar);

            followRepository.saveAll(Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8));
        };
    }
}
