package com.efubtoy.team1.domain.account.test;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.account.repository.AccountRepository;
import com.efubtoy.team1.domain.auth.annotation.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class testController {
    private final AccountRepository accountRepository;

    //이 함수는 테스트 함수입니다. 실제 api가 아닙니다.
    @GetMapping("/test")
    public ResponseEntity authuser_annotation_test(){
        String email = "yuasdlfkj";
        String nickname="sjf;lk";
        Optional<Account> account=accountRepository.findByEmail(email);
        if (account==null){
            Account newAccount = Account.builder()
                    .nickname(nickname)
                    .email(email)
                    .build();
            accountRepository.save(newAccount);
        }
        return ResponseEntity.ok().build();
    }
}
