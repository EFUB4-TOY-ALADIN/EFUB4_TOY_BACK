package com.efubtoy.team1.domain.book.service;

import com.efubtoy.team1.domain.book.domain.Book;
import com.efubtoy.team1.domain.book.domain.UsedBook;
import com.efubtoy.team1.domain.book.domain.category.IsDomestic;
import com.efubtoy.team1.domain.book.dto.BookInfoResponseDto;
import com.efubtoy.team1.domain.book.dto.BookResponseDto;
import com.efubtoy.team1.domain.book.repository.BookRepository;
import com.efubtoy.team1.domain.book.repository.UsedBookRepository;
import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.domain.UsedRecord;
import com.efubtoy.team1.domain.record.dto.response.RecordResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UsedBookRepository usedBookRepository;

    public Book findBookById(Long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(()->new IllegalArgumentException("Unexpected book"));
    }

    public List<Book> findBookByCategories(Long isDomestic,
                                           Long category,
                                           Long topic){
        Boolean isDomesticB = IsDomestic.getIsDomesticByDomesticNum(isDomestic);
        return bookRepository.findByIsDomesticAndCategoryAndTopic(isDomesticB, category, topic);

    }

    public List<BookResponseDto> findBookWithLowestAndStock(List<Book> bookList){
        if (bookList == null || bookList.isEmpty()) {
            return Collections.emptyList(); // 빈 리스트 반환
        }
        return bookList.stream()
                .map(book -> {
                    List<UsedBook> usedBookList = usedBookRepository.findAllByBook(book);
                    Long lowestPrice = (long) usedBookRepository.findAllByBookOrderByPrice(book).get(0).getPrice();
                    Long stock = (long) usedBookList.size();

                    return BookResponseDto.of(book, lowestPrice , stock);
                })
                .collect(Collectors.toList());
    }
}
