package com.efubtoy.team1.domain.book.service;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.UsedBook;
import com.efubtoy.team1.domain.book.dto.BookInfoResponseDto;
import com.efubtoy.team1.domain.book.repository.UsedBookRepository;
import com.efubtoy.team1.global.exception.CustomException;
import com.efubtoy.team1.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsedBookService {
    private final UsedBookRepository usedBookRepository;

    private final BookService bookService;

    public UsedBook findUsedBookById(Long usedBookId){
        return usedBookRepository.findById(usedBookId)
                .orElseThrow(()->new CustomException(ErrorCode.USED_BOOK_NOT_FOUND));
    }

    /* 상세 도서 목록 조회 */
    public ResponseEntity<BookInfoResponseDto> findUsedBookList(Long bookId) {
        Book book = bookService.findBookById(bookId);
        List<UsedBook> usedBookList = usedBookRepository.findAllByBook(book);
        return ResponseEntity.status(HttpStatus.OK)
                .body(createBookInfoResponseDto(book,usedBookList));
    }

    /* 상세 도서 목록 조회 응답 dto 생성 */
    public BookInfoResponseDto createBookInfoResponseDto(Book book, List<UsedBook> usedBookList){
        Long lowestPrice = usedBookRepository.findAllByBookOrderByPrice(book).get(0).getPrice();
        Long stock = (long) usedBookList.size();
        return BookInfoResponseDto.of(book,lowestPrice,stock,usedBookList);
    }
}
