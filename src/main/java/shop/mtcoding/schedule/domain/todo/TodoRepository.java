package shop.mtcoding.schedule.domain.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select to from Todo to where to.user.id = :userId")
    List<Todo> findByUserId(@Param("userId") Long userId);
}
