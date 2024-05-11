package com.efubtoy.team1.domain.auth.dto;

import com.efubtoy.team1.domain.account.dto.AccountRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {

    private AccountRequestDTO userinfo;
    private String accessToken;
    private String refreshToken;

}
