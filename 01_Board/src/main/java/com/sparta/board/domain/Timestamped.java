package com.sparta.board.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter // 데이터 조회를 위해서는 Getter가 필요하다.
@MappedSuperclass // Entity가 자동으로 컬럼으로 인식합니다.
@EntityListeners(AuditingEntityListener.class) // 생성/변경 시간을 자동으로 업데이트합니다. 메모클래스가 변하는 것을 지켜보다가 변경하겠다.
//abstract : 상속 받아야만 사용 가능
public abstract class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt; // getter가 필요하다



    @LastModifiedDate
    private LocalDateTime modifiedAt;
}

