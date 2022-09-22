package org.zerock.decommi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class ApiMemberController {
  private final MemberService service;

  // 멤버 하나 가져오기
  @RequestMapping(value = "/getAuth", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> getAuth(@RequestBody Map<String, Object> mapObj,
      @RequestHeader("token") String token) {
    String email = mapObj.get("email").toString();
    Map<String, Object> map = new HashMap<>();
    map.put("dto", service.getMemberDTO(email));
    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
  }

  // 이메일체크
  @RequestMapping(value = "/emailCheck", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Long>> emailCheck(
      @RequestBody Map<String, Object> mapObj,
      @RequestHeader("token") String token) {
    String email = mapObj.get("email").toString();
    MemberDTO dto = service.emailCheck(email);

    Map<String, Long> mapForResult = new HashMap<>();
    mapForResult.put("result", (dto == null) ? 0L : 1L);

    return new ResponseEntity<Map<String, Long>>(mapForResult, HttpStatus.OK);
  }

  // 회원가입
  @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> signUp(@RequestBody MemberDTO dto) {
    log.info("member/signup : " + dto);
    String email = service.signUp(dto);
    return new ResponseEntity<>(email, HttpStatus.OK);
  }

  // @RequestMapping(value = "/getlist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<List<MemberDTO>> getList(
  //     @RequestHeader("token") String token) {
  //   List<MemberDTO> result = service.getList();
  //   log.info(result);
  //   return new ResponseEntity<>(result, HttpStatus.OK);
  // }

  // @RequestMapping(value = "/get-page-list", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  // public ResponseEntity<PageResultDTO<MemberDTO, Member>> getPagingList(
  //     @RequestBody PageRequestDTO dto,
  //     @RequestHeader("token") String token) {
  //   log.info("PageRequestDTO page: " + dto.getPage());
  //   return new ResponseEntity<>(service.getPageList(dto), HttpStatus.OK);
  // }

}
