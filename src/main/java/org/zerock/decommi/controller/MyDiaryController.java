package org.zerock.decommi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.service.diary.MyDiaryService;
import org.zerock.decommi.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mydiary/")
@Log4j2
public class MyDiaryController {
  private final DiaryService diaryService;
  private final MyDiaryService mdService;
  // private final String secretKey = "decommi1q2w3e4r!";

  @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DiaryDTO>> getMyDiaryList(@RequestBody PageRequestDTO dto) {
    PageRequestDTO.builder()
        .page(dto.getPage()).size(5).writer(dto.getWriter()).build();
    PageResultDTO<DiaryDTO, Diary> result = mdService.getMyDiaryPostList(dto);
    log.info("dto ::::: " + dto);
    log.info("result ::::::" + result);
    return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  }

  @RequestMapping(value = "/list/search", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DiaryDTO>> getSearchMyDiaryList(@RequestBody PageRequestDTO dto) {
    // <String> type : null 값이면 검색조건없이 모든 게시글 리스트 반환,
    // <String> type : 's'를 보내주면 검색조건 결과 만족하는 리스트 반환
    // <String> keyword : 검색문자열 title, 또는 content에 해당 문자열이 들어있는 결과 리스트 반환
    // <String> keyword : 없을시 반환안함.
    // List<String> tagList : 게시글에 해당 태그가 포함된 결과반환 여러개가 될 수 있고 하나가 될 수 있다.
    log.info("Controller 프론트에서 보내주는 검색조건 dto:::::" + dto);
    log.info("controller dto tagList : " + dto.getTagList());
    log.info("controller dto type : " + dto.getType());
    log.info("controller dto keyword : " + dto.getKeyword());
    log.info("controller dto writer : "+ dto.getWriter());

    PageRequestDTO.builder().page(dto.getPage()).size(5)
        .type(dto.getType()).keyword(dto.getKeyword()).writer(dto.getWriter()).tagList(dto.getTagList()).build();
    PageResultDTO<DiaryDTO, Diary> result = mdService.getSearchMyDiaryPostList(dto);
    return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  }

  @RequestMapping(value = "/read/{dino}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> DiaryRead(@ModelAttribute("dino") Long dino) {
    HashMap<String, Object> result = new HashMap<>();
    DiaryDTO diaryPost = diaryService.getDiaryPostByDino(dino);
    log.info("diaryPost" + diaryPost);
    result.put("diaryPost", diaryPost);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  // @RequestMapping(value = "/liketaglist", method = RequestMethod.POST, consumes
  // = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<List<DiaryDTO>> getDiaryList(@ModelAttribute
  // LikeTagListDTO dto) {
  // PageRequestDTO.builder().page(10).size(30).type(dto.getType()).keyword(dto.getKeyword()).tagList(dto.getTagList()).build();
  // PageResultDTO<DiaryDTO, Diary> result = mdService.LikeTagList(dto);
  // return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  // }

}
