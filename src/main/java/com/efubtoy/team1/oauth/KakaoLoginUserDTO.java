package com.efubtoy.team1.oauth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class KakaoLoginUserDTO {
    private String nickname;
    private String email;
}
