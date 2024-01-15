package com.dudgns.purchase.entity;

import com.dudgns.purchase.entity.id.UserTokenId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = UserTokenEntity.TABLE_NAME)
public class UserTokenEntity {
    public static final String TABLE_NAME = "user_token";

    @EmbeddedId
    private UserTokenId id;

    private LocalDateTime expireDate;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
