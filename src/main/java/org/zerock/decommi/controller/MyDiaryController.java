package org.zerock.decommi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.service.diary.MyDiaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mydiary/")
@Log4j2
public class MyDiaryController {
  private final MyDiaryService mdService;
  private final String secretKey = "decommi1q2w3e4r!";

  @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DiaryDTO>> getMyDiaryList(@ModelAttribute PageRequestDTO dto, String id) {
    
    PageRequestDTO.builder().page(10).size(30).type(dto.getType()).keyword(dto.getKeyword()).tagList(dto.getTagList()).build();
    PageResultDTO<DiaryDTO, Diary> result = mdService.getMyDiaryPostList(dto);
    return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  }
}
