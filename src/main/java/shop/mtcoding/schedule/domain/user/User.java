package shop.mtcoding.schedule.domain.user;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.schedule.handler.ex.CustomApiException;
import shop.mtcoding.schedule.util.CustomDateUtil;

@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_tb")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;
    @Column(nullable = false, length = 60) // 패스워드 인코딩하면 길어짐
    private String password;
    @Column(nullable = false, length = 20)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserEnum role;

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
    public User(Long id, String username, String password, String email, UserEnum role,
            LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public void authenticationPassword(String currentPassword, BCryptPasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(currentPassword, this.password)) {
            throw new CustomApiException("패스워드 인증에 실패하였습니다");
        }
    }

    public void checkSamePassword(String newPassword, BCryptPasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(newPassword, this.password)) {
            throw new CustomApiException("새로운 패스워드가 현재 패스워드와 동일합니다");
        }
    }

    public void updatePassword(String currentPassword, String newPassword,
            BCryptPasswordEncoder passwordEncoder) {
        authenticationPassword(currentPassword, passwordEncoder);
        checkSamePassword(newPassword, passwordEncoder);
        this.password = passwordEncoder.encode(newPassword);
    }

}
