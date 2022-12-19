package shop.mtcoding.schedule.domain.category;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.schedule.util.CustomDateUtil;

@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "category_tb")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private CategoryEnum color;
    private String name;

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
    public Category(Long id, CategoryEnum color, String name, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

}
