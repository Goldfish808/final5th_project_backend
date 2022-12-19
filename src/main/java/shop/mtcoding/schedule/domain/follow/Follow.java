package shop.mtcoding.schedule.domain.follow;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.util.CustomDateUtil;

@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "follow_tb")
@Entity
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;

    private Boolean isActive; // 초기값 true로 잡기

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    public String getCreatedAt() {
        return CustomDateUtil.toStringFormat(createdAt);
    }

    public String getUpdatedAt() {
        return CustomDateUtil.toStringFormat(createdAt);
    }

    @Builder
    public Follow(Long id, User fromUser, User toUser, Boolean isActive, LocalDateTime updatedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.isActive = isActive;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

}
