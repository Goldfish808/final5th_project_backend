package shop.mtcoding.schedule.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
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
import shop.mtcoding.schedule.dto.request.ScheduleReqDto;
import shop.mtcoding.schedule.dto.response.HomeRespDto;
import shop.mtcoding.schedule.handler.ex.CustomApiException;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;
    private final FollowRepository followRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public HomeRespDto findHome(Long userId, String startAt) {

        System.out.println("디버그 : startAt : " + startAt);
        List<Schedule> sList = scheduleRepository.findByStartAt(userId, startAt);
        List<Todo> tList = todoRepository.findByUserId(userId);

        List<Follow> fList = followRepository.findFollowing(userId);

        List<User> toUsers = fList.stream().map((f) -> f.getToUser()).collect(Collectors.toList());

        return new HomeRespDto(sList, tList, toUsers);
    }

    public List<Schedule> findCalender(Long userId, String startAt) {
        List<Schedule> sList = scheduleRepository.findByStartAt(userId, startAt);
        return sList;
    }

    @Transactional
    public Schedule 스케줄등록(MultipartFile imgFile, ScheduleReqDto scheduleReqDto, Long userId) {
        String categoryName = scheduleReqDto.getCategoryName();

        Category categoryPS = categoryRepository.findByName(categoryName).orElseThrow(
                () -> new CustomApiException("카테고리를 찾을 수 없습니다"));

        User userPS = userRepository.findById(userId).orElseThrow(
                () -> new CustomApiException("해당 유저를 찾을 수 없습니다"));

        String filePath = "src\\main\\resources\\static\\";

        String imgUrl = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();

        Path imageFilePath = Paths.get(filePath + imgUrl);

        try {
            Files.write(imageFilePath, imgFile.getBytes());
        } catch (IOException e) {
            throw new CustomApiException("스케줄 사진 저장 실패");
        }

        Schedule schedule = scheduleReqDto.toEntity(imgUrl, categoryPS, userPS);
        Schedule schedulePS = scheduleRepository.save(schedule);
        return schedulePS;
    }

}
