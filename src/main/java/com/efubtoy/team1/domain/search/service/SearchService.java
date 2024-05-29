package com.efubtoy.team1.domain.search.service;

import com.efubtoy.team1.domain.book.dto.BookDto;
import com.efubtoy.team1.domain.book.dto.BookListResponseDto;
import com.efubtoy.team1.domain.book.repository.BookRepository;
import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import com.efubtoy.team1.domain.goods.repository.GoodsRepository;
import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.dto.response.RecordListResponseDto;
import com.efubtoy.team1.domain.record.dto.response.RecordResponseDto;
import com.efubtoy.team1.domain.record.repository.RecordRepository;
import com.efubtoy.team1.domain.record.service.RecordService;
import com.efubtoy.team1.domain.search.dto.response.SearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final BookRepository bookRepository;
    private final RecordRepository recordRepository;
    private final GoodsRepository goodsRepository;

    private final RecordService recordService;


    /*제목에 해당하는 단어가 포함되어있는지 찾기 */
    public SearchResponseDto Search(String searchWord){
        //Book
        List<BookDto> bookList = bookRepository.findByTitle(searchWord).orElseGet(Collections::emptyList);
        //Record
        List<Record> recordList = recordRepository.findByTitle(searchWord).orElseGet(Collections::emptyList);
        List<RecordResponseDto> recordList1 = recordService.findRecordWithLowestAndStock(recordList);

        //Goods
        List<GoodsDto> goodsList = goodsRepository.findByTitle(searchWord).orElseGet(Collections::emptyList);

        return new SearchResponseDto(searchWord , bookList , recordList1  , goodsList);
    }
}
