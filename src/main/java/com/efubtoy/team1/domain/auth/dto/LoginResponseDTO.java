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
    //프런트엔드 요청으로 리뷰 삭제 시 작성자 유무 구분하기 위해 accountId 추가
    private long accountId;
    private String accessToken;
    private String refreshToken;

}
