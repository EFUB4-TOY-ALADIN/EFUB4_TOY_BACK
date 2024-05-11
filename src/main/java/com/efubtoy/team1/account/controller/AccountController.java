package com.efubtoy.team1.account.controller;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.account.dto.AccountDTO;
import com.efubtoy.team1.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody AccountDTO dto){
        System.out.println("AccountController.join");
        Account account = Account.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .build();
        accountRepository.save(account);

        return ResponseEntity.ok().body(dto);
    }



}
