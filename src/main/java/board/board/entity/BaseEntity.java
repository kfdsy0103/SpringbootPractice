package board.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
abstract class BaseEntity {

    @CreatedDate
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}
// 객체의 생성/수정 시간을 자동으로 처리하도록 어노테이션을 사용
// @CreatedDate : 생성 시간 처리, @LastModifiedDate : 수정 시간 처리
// MappedSuperclass가 붙으면 테이블로 생성되지 않고 이 클래스를 상속받은 엔티티의 클래스로 테이블이 생성됨
// JPA에서 엔티티는 JPA만의 고유한 메모리 공간(context)에서 관리되고,
// 엔티티의 생성/변경을 감지하는 역할은 AuditingEntityListener가 맡게 됨.
// 리스너 활성화를 위해 BoardApplication에 @EnableJpaAuditing 추가
