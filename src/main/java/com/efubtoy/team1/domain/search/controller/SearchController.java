package com.efubtoy.team1.domain.search.controller;

import com.efubtoy.team1.domain.record.repository.RecordRepository;
import com.efubtoy.team1.domain.search.dto.response.SearchResponseDto;
import com.efubtoy.team1.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /* 통합 검색 */
    @GetMapping
    public ResponseEntity<SearchResponseDto> SearchAllByWord(@RequestParam(name ="word") String searchWord){
          SearchResponseDto searchResponseDto = searchService.Search(searchWord);
          return ResponseEntity.status(HttpStatus.OK).body(searchResponseDto);
    }




}
