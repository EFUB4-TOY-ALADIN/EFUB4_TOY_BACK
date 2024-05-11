package com.efubtoy.team1.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class AccountResponseDTO {
    private Long id;
    private String nickname;
    private String email;
}
