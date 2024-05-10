package com.efubtoy.team1.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAccountResponse {
    private String nickname;
    private String email;
    private String accessToken;
}
