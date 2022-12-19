package shop.mtcoding.schedule.domain.todo;

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
@Table(name = "todo_tb")
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titie;
    private Boolean isFinished;

    @ManyToOne
    private User user;

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
    public Todo(Long id, String titie, Boolean isFinished, User user, LocalDateTime updatedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.titie = titie;
        this.isFinished = isFinished;
        this.user = user;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

}
