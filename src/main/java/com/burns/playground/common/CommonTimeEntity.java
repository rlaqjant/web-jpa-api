package com.burns.playground.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * packageName    : com.burns.playground.common
 * fileName       : CommonTimeEntity
 * author         : beomsu
 * date           : 2023-01-12
 * description    : 반복 사용 컬럼 - 생성시간, 수정시간
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-01-12        beomsu       최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CommonTimeEntity
{
    @CreatedDate
    private LocalDateTime createDt;
    @LastModifiedDate
    private LocalDateTime modifyDt;
}
