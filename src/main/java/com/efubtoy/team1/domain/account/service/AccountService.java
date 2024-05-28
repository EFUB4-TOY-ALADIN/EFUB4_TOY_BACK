package com.efubtoy.team1.domain.account.service;

import com.efubtoy.team1.domain.account.domain.Account;
import com.efubtoy.team1.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public long findAccountIdByEmail(String email){
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected account"));
        return account.getAccountId();
    }

    public Account findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(()->new IllegalArgumentException("Unexpected account"));
    }
}
