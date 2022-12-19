package shop.mtcoding.schedule.domain.schedule;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.schedule.domain.category.Category;
import shop.mtcoding.schedule.domain.user.User;
import shop.mtcoding.schedule.util.CustomDateUtil;

@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "schedule_tb")
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String note;
    private String address;
    private String imgUrl;

    @OneToOne(fetch = FetchType.EAGER)
    private Category category;

    private LocalDateTime startAt;
    private LocalDateTime finishAt;

    // 연관 관계
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

    public String getStartAt() {
        return CustomDateUtil.toStringFormat(startAt);
    }

    public String getFinishAt() {
        return CustomDateUtil.toStringFormat(finishAt);
    }

    @Builder
    public Schedule(Long id, String title, String note, String address, String imgUrl, Category category,
            LocalDateTime startAt, LocalDateTime finishAt, User user, LocalDateTime updatedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.address = address;
        this.imgUrl = imgUrl;
        this.category = category;
        this.startAt = startAt;
        this.finishAt = finishAt;
        this.user = user;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

}
