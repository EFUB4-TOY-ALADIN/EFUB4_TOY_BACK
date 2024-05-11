package com.efubtoy.team1.book.service;

import com.efubtoy.team1.book.domain.UsedBook;
import com.efubtoy.team1.book.repository.UsedBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsedBookService {
    private final UsedBookRepository usedBookRepository;
    public UsedBook findUsedBookById(Long usedBookId){
        return usedBookRepository.findById(usedBookId)
                .orElseThrow(()->new IllegalArgumentException("Unexpected UsedBook"));
    }
}
