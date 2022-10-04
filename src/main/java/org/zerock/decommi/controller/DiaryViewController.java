package org.zerock.decommi.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.service.diary.DiaryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/diary/")
@RequiredArgsConstructor
public class DiaryViewController {
  private final DiaryService diaryService;


  @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DiaryDTO>> getDiaryList(@RequestBody PageRequestDTO dto) {
    // <String> type : null 값이면 검색조건없이 모든 게시글 리스트 반환,
    // <String> type : 's'를 보내주면 검색조건 결과 만족하는 리스트 반환
    // <String> keyword : 검색문자열 title, 또는 content에 해당 문자열이 들어있는 결과 리스트 반환
    // <String> keyword : 없을시 반환안함.
    // List<String> tagList : 게시글에 해당 태그가 포함된 결과반환 여러개가 될 수 있고 하나가 될 수 있다. 해당 태그가
    // 하나라도 존재하는 결과 반환
    //
    log.info("Controller 프론트에서 보내주는 검색조건 dto:::::" + dto);
    log.info("controller dto tagList : " + dto.getTagList());
    log.info("controller dto type : " + dto.getType());
    log.info("controller dto keyword : " + dto.getKeyword());
    PageRequestDTO.builder().page(dto.getPage()).size(5).type(dto.getType()).keyword(dto.getKeyword())
        .tagList(dto.getTagList())
        .build();
    PageResultDTO<DiaryDTO, Diary> result = diaryService.getDiaryPostList(dto);
    result.getDtoList().forEach(v -> {
      log.info(v.getTitle());
    });
    log.info(result);
    return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  }

  @RequestMapping(value = "/list/search", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DiaryDTO>> getSearchDiaryList(@RequestBody PageRequestDTO dto) {
    // <String> type : null 값이면 검색조건없이 모든 게시글 리스트 반환,
    // <String> type : 's'를 보내주면 검색조건 결과 만족하는 리스트 반환
    // <String> keyword : 검색문자열 title, 또는 content에 해당 문자열이 들어있는 결과 리스트 반환
    // <String> keyword : 없을시 반환안함.
    // List<String> tagList : 게시글에 해당 태그가 포함된 결과반환 여러개가 될 수 있고 하나가 될 수 있다. 해당 태그가
    // 하나라도 존재하는 결과 반환
    //
    log.info("Controller 프론트에서 보내주는 검색조건 dto:::::" + dto);
    log.info("controller dto tagList : " + dto.getTagList());
    log.info("controller dto type : " + dto.getType());
    log.info("controller dto keyword : " + dto.getKeyword());
    PageRequestDTO.builder().page(dto.getPage()).size(5).type(dto.getType()).keyword(dto.getKeyword())
        .tagList(dto.getTagList())
        .build();
    PageResultDTO<DiaryDTO, Diary> result = diaryService.getDiaryPostList(dto);
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

  @GetMapping("/image/{fileName}")
  public ResponseEntity<byte[]> getFile(@ModelAttribute("fileName") String fileName, String size) {
    ResponseEntity<byte[]> result = null;
    try {
      String srchFileName = URLDecoder.decode(fileName, "UTF-8");
      File file = new File("c:\testingimage" + File.separator + srchFileName);// window일경우 사용
      // File.separator + srchFileName);
      if (size != null && size.equals("1")) {
        file = new File(file.getParent(), file.getName().substring(2));
      }

      HttpHeaders header = new HttpHeaders();
      header.add("Content-Type", Files.probeContentType(file.toPath())); // MIME
      result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
      log.info("fffffffffffffff" + fileName);
      log.info(result);
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return result;
  }
}
