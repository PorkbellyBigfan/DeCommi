package org.zerock.decommi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.service.member.LikeTagListService;
import org.zerock.decommi.service.member.MemberService;
import org.zerock.decommi.vo.Findpw;
import org.zerock.decommi.vo.Setpw;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberApiController {
  private final MemberService service;
  private final LikeTagListService likeTagListService;

  // 회원가입
  @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> signUp(@RequestBody MemberDTO dto) {
    log.info("controller class member/signup : " + dto);
    String email = service.signUp(dto);
    log.info("controller class 회원가입이 완료된 email ::" + email);
    return new ResponseEntity<>(email, HttpStatus.OK);
  }

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
      @RequestBody Map<String, Object> mapObj) {
    String email = mapObj.get("email").toString();
    MemberDTO dto = service.emailCheck(email);

    Map<String, Long> mapForResult = new HashMap<>();
    mapForResult.put("result", (dto == null) ? 0L : 1L);

    return new ResponseEntity<Map<String, Long>>(mapForResult, HttpStatus.OK);
  }

  // 회원정보수정 눌렀을때 비밀번호를 확인(입력한 비밀번호가 DB에 저장되어있는 회원의 비밀번호와 일치하는지)
  @RequestMapping(value = "/checkpw", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> pwCheck(@RequestBody MemberDTO dto) {
    // email : 현재 로그인하고있는 회원의 email
    // pw : 회원정보수정 눌렀을때 사용자가 입력한 비밀번호
    String email = dto.getEmail();
    String pw = dto.getPw();
    // 사용자가 입력한 비밀번호가 해당 계정의 암호화되어있는 비밀번호와 일치하는 지 확인하는 매서드
    // 일치하면 true 반환 , 일치하지 않을시 false 반환
    Boolean result = service.pwCheck(email, pw);
    log.info("service checkpw :::: " + dto);
    log.info("service checkpw result ::" + result);
    return new ResponseEntity<Boolean>(result, HttpStatus.OK);
  }

  // 비밀번호 재설정
  @RequestMapping(value = "/setpw", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> setpw(@RequestBody Setpw vo) {
    log.info(" 사용자가 입력한 값들 vo ::::: " + vo);
    return new ResponseEntity<>(service.changePw(vo), HttpStatus.OK);
  }

  // 로그인시 이메일 까먹었을때
  @RequestMapping(value = "findemail", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> findemail(@RequestBody MemberDTO email) {
    log.info(email);
    return new ResponseEntity<>(service.findEmail(email), HttpStatus.OK);
  }

  // 로그인시 비밀번호 까먹었을때
  @RequestMapping(value = "/findpw", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> findpw(@RequestBody Findpw vo) {
    log.info(vo);
    log.info(service.findPw(vo));
    return new ResponseEntity<>(service.findPw(vo), HttpStatus.OK);
  }

  // 선호태그 리스트 출력
  @RequestMapping(value = "/liketaglist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Optional<List<String>>> getLikeTagList(@RequestBody String email) {
    return new ResponseEntity<>(likeTagListService.getLikeTagList(email), HttpStatus.OK);
  }

  // 선호태그 변경
  @RequestMapping(value = "/editliketaglist", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Boolean> reportingDiary(@RequestBody LikeTagListDTO dto) {
    return new ResponseEntity<>(likeTagListService.editLikeTagList(dto), HttpStatus.OK);
  }
}
