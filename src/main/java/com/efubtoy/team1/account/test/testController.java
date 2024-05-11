package com.efubtoy.team1.account.test;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.auth.annotation.AuthUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    //이 함수는 테스트 함수입니다. 실제 api가 아닙니다.
    @GetMapping("/test")
    public ResponseEntity authuser_annotation_test(@AuthUser Account account){
        System.out.println(account.getAccountId());
        System.out.println(account.getNickname());
        return ResponseEntity.ok().build();
    }
}
