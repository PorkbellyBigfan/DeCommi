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
    PageRequestDTO.builder().page(dto.getPage()).size(5).keyword(dto.getKeyword()).build();
    PageResultDTO<DiaryDTO, Diary> result = diaryService.getDiaryPostList(dto);
    // log.info("===============================");
    // log.info(" controller class dto::::" + dto);
    log.info("result.getDtoList() " + result.getDtoList().get(0).getReplyList());
    // log.info("===============================");
    return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  }

  // 구현 못함
  // @RequestMapping(value = "/list/search", method = RequestMethod.POST, consumes
  // = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<HashMap<String, Object>>
  // getSearchDiaryList(@RequestBody PageRequestDTO dto) {
  // HashMap<String, Object> result = diaryService.getSearchDiaryPostList(dto);
  // log.info("===============================");
  // log.info("controller class dto :::"+dto);
  // log.info("dto's type ::: "+dto.getType());
  // // log.info("controller class result :::"+result);
  // log.info("result ' s dto ::: "+ result.get("dto"));
  // log.info("result ' s page ::: "+ result.get("page"));
  // log.info("result ' s size ::: "+ result.get("totalPage"));
  // log.info("===============================");
  // return new ResponseEntity<>(result,HttpStatus.OK);
  // // return null;
  // }

  // 태그를 눌렀을때 그 해당 태그를 포함하고있는 게시글 리스트반환 여기서 String tagName은 해당 태그의 tagName임.
  @RequestMapping(value = "/list/bytagname", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<DiaryDTO>> getDiaryLisyByTagName(@RequestBody PageRequestDTO dto) {
    PageRequestDTO.builder().page(dto.getPage()).size(5).tagList(dto.getTagList()).tagName(dto.getTagName()).build();
    log.info("Controller class  dto ::: " + dto);
    log.info("Controller class  dto.getTagName ::: " + dto.getTagName());
    PageResultDTO<DiaryDTO, Diary> result = diaryService.getDiaryPostListByTagName(dto);
    result.getDtoList().forEach(v -> {
      log.info("getTitle:::" + v.getTitle());
      log.info("getTagList:::" + v.getTagList());
    });
    return new ResponseEntity<>(result.getDtoList(), HttpStatus.OK);
  }

  @RequestMapping(value = "/read/{dino}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> DiaryRead(@ModelAttribute("dino") Long dino) {
    HashMap<String, Object> result = new HashMap<>();
    DiaryDTO diaryPost = diaryService.getDiaryPostByDino(dino);
    log.info("=======================================");
    log.info("diaryPost" + diaryPost);
    log.info("=======================================");
    result.put("diaryPost", diaryPost);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/image/{fileName}")
  public ResponseEntity<byte[]> getFile(@ModelAttribute("fileName") String fileName, String size) {
    ResponseEntity<byte[]> result = null;
    try {
      String srchFileName = URLDecoder.decode(fileName, "UTF-8");
      File file = new File("c:\\testingimage" + File.separator + srchFileName);// window일경우 사용
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
