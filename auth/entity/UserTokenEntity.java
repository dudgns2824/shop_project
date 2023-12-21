package com.dudgns.backendauth.entity;

import com.dudgns.backendauth.entity.id.UserTokenId;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = UserEntity.TABLE_NAME, uniqueConstraints = {
        @UniqueConstraint(name = "uc_usertokenentity_userguid", columnNames = {"UserGUID"})
})
public class UserTokenEntity{
    public static final String TABLE_NAME = "user_token";

    @EmbeddedId
    private UserTokenId id;

    private LocalDateTime expireDate;

    @CreationTimestamp
    private LocalDateTime createdDate;
}
