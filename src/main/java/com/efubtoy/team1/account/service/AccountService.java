package com.efubtoy.team1.account.service;

import com.efubtoy.team1.account.domain.Account;
import com.efubtoy.team1.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;


    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(()->new IllegalArgumentException("Unexpected account"));
    }
}
