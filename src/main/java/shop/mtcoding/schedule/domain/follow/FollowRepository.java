package shop.mtcoding.schedule.domain.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    @Query("select f from Follow f where f.fromUser.id = :fromUserId and f.isActive = true")
    List<Follow> findFollowing(@Param("fromUserId") Long fromUserId);

    @Query("select f from Follow f where f.toUser.id = :toUserId and f.isActive = true")
    List<Follow> findFollower(@Param("toUserId") Long toUserId);

    @Query(value = "select * from follow_tb where from_user_id = :loginUserId and to_user_id = :detailPageUserId and is_active = true", nativeQuery = true)
    Follow findIsFollow(@Param("loginUserId") Long loginUserId, @Param("detailPageUserId") Long detailPageUserId);

    @Query("select f from Follow f where f.toUser.id = :toUserId and f.fromUser.id = :fromUserId")
    Follow findByFollowCheck(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);

    @Query("select f from Follow f where f.toUser.id = :toUserId and f.fromUser.id = :fromUserId and f.isActive = true")
    Follow findByUnFollowCheck(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
}
