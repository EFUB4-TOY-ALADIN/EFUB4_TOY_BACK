package com.efubtoy.team1.domain.book.service;

import com.efubtoy.team1.domain.book.domain.UsedBook;
import com.efubtoy.team1.domain.book.repository.UsedBookRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
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
                .orElseThrow(()->new CustomException(ErrorCode.USED_BOOK_NOT_FOUND));
    }
}
