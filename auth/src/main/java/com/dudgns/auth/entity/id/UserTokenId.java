package com.dudgns.auth.entity.id;

import jakarta.persistence.Column;
import lombok.*;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTokenId implements Serializable {
    @Column(name = "user_guid", nullable = false)
    private UUID userGUID;
    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;
}
