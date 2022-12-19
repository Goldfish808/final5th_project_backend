package shop.mtcoding.schedule.domain.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value = "select * from schedule_tb WHERE DATE(start_at) = :startAt and user_id = :userId", nativeQuery = true)
    List<Schedule> findByStartAt(@Param("userId") Long userId, @Param("startAt") String startAt);

    @Query(value = "select * from schedule_tb WHERE user_id = :userId", nativeQuery = true)
    List<Schedule> findByUserId(@Param("userId") Long userId);
}
