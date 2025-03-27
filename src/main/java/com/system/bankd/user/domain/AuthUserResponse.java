package com.system.bankd.user.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthUserResponse {
    private Long userId;
    private String userName;
    private String fullName;

    public AuthUserResponse(Long userId, String userName, String fullName) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
    }
}
