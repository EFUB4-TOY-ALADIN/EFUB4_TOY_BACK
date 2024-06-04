package com.efubtoy.team1.domain.account.test;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.account.dto.AccountRequestDTO;
import com.efubtoy.team1.domain.account.repository.AccountRepository;
import com.efubtoy.team1.domain.auth.annotation.AuthUser;
import com.efubtoy.team1.global.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class testController {
    private final AccountRepository accountRepository;
    private final JWTUtils jwtUtils;

    //이 함수는 테스트 함수입니다. 실제 api가 아닙니다.
    @GetMapping("/test")
    public ResponseEntity authuser_annotation_test(){
        String email = "yujinalice00@gmail.com";
        String nickname="정유진";
        AccountRequestDTO build = AccountRequestDTO.builder().email(email).nickname(nickname).build();
        String token = "Bearer "+jwtUtils.createToken(build);

        return ResponseEntity.ok().body(token);
    }
}
