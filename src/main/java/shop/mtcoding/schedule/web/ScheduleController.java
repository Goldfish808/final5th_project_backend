package shop.mtcoding.schedule.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.schedule.config.auth.LoginUser;
import shop.mtcoding.schedule.domain.schedule.Schedule;
import shop.mtcoding.schedule.domain.schedule.ScheduleRepository;
import shop.mtcoding.schedule.domain.todo.TodoRepository;
import shop.mtcoding.schedule.dto.request.ScheduleReqDto;
import shop.mtcoding.schedule.dto.response.ResponseDto;
import shop.mtcoding.schedule.service.ScheduleService;

@RequiredArgsConstructor
@RestController
public class ScheduleController {
    private final ScheduleRepository scheduleRepository;
    private final TodoRepository todoRepository;
    private final ScheduleService scheduleService;

    @PostMapping("/s/schedule")
    public ResponseEntity<?> save(@RequestPart("imgFile") MultipartFile imgFile,
            @RequestPart("scheduleReqDto") ScheduleReqDto scheduleReqDto,
            @AuthenticationPrincipal LoginUser loginUser) {
        System.out.println("디버그 : file : " + imgFile.getOriginalFilename());
        System.out.println("디버그 : scheduleReqDto : " + scheduleReqDto.getNote());

        Schedule schedule = scheduleService.스케줄등록(imgFile, scheduleReqDto, loginUser.getUser().getId());

        return new ResponseEntity<>(new ResponseDto<>(1, "성공", schedule), HttpStatus.OK);
    }

    @GetMapping("/s/schedule")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(new ResponseDto<>(1, "성공", scheduleRepository.findAll()), HttpStatus.OK);
    }

    // http://localhost:8080/s/home?startAt=2022-12-19 (토큰) (Get)
    @GetMapping("/s/home")
    public ResponseEntity<?> home(
            @AuthenticationPrincipal LoginUser loginUser, String startAt) {
        return new ResponseEntity<>(
                new ResponseDto<>(1, "성공", scheduleService.findHome(loginUser.getUser().getId(), startAt)),
                HttpStatus.OK);
    }

    @GetMapping("/s/calender")
    public ResponseEntity<?> calender(@AuthenticationPrincipal LoginUser loginUser, String startAt) {
        return new ResponseEntity<>(
                new ResponseDto<>(1, "성공", scheduleService.findCalender(loginUser.getUser().getId(), startAt)),
                HttpStatus.OK);
    }

}
