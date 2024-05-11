package com.efubtoy.team1.account.controller;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.account.dto.AccountRequestDTO;
import com.efubtoy.team1.account.dto.AccountResponseDTO;
import com.efubtoy.team1.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    @PostMapping("/join")
    public ResponseEntity join(@RequestBody AccountRequestDTO dto){
        System.out.println("AccountController.join");
        Account account = Account.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .build();
        Account newAccount = accountRepository.save(account);


        return ResponseEntity.ok().body(AccountResponseDTO.builder()
                .id(newAccount.getAccountId())
                .nickname(newAccount.getNickname())
                .email(newAccount.getEmail())
                .build());
    }

    @GetMapping("/{accountId}")
    public ResponseEntity findAccountById(@PathVariable("accountId")Long accountId){
        Account findAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("회원가입되지 않은 사용자입니다. 회원가입을 진행해주십시오."));
        return ResponseEntity.ok().body(AccountResponseDTO.builder()
                .id(findAccount.getAccountId())
                .nickname(findAccount.getNickname())
                .email(findAccount.getEmail())
                .build());
    }



}
