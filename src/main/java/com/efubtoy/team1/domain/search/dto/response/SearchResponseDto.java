package com.efubtoy.team1.domain.search.dto.response;


import com.efubtoy.team1.domain.book.dto.BookDto;
import com.efubtoy.team1.domain.book.dto.BookListResponseDto;
import com.efubtoy.team1.domain.book.dto.BookResponseDto;
import com.efubtoy.team1.domain.goods.dto.GoodsDto;
import com.efubtoy.team1.domain.record.domain.Record;
import com.efubtoy.team1.domain.record.dto.response.RecordListResponseDto;
import com.efubtoy.team1.domain.record.dto.response.RecordResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchResponseDto {


    private String word;

    private List<BookResponseDto>  bookList = new ArrayList<>();
    private List<RecordResponseDto> recordList = new ArrayList<>();
    private List<GoodsDto> goodsList = new ArrayList<>();

    public static SearchResponseDto of(String searchWord , List<BookResponseDto> bookList ,
                                       List<RecordResponseDto> recordList , List<GoodsDto> goodsList){
        return new SearchResponseDto(
                searchWord,
                bookList,
                recordList,
                goodsList
        );
    }


}
