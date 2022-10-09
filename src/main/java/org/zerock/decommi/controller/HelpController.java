package org.zerock.decommi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.decommi.service.HelpService;
import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.Help;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/help")
public class HelpController {
    private final HelpService helpService;

    // token보내주기
    @PostMapping(value = "/notice")
    public ResponseEntity<PageResultDTO<HelpDTO, Help>> getNoticeList(@RequestBody PageRequestDTO req) {
        log.info("PageRequest:" + req);
        return new ResponseEntity<>(helpService.getNoticeList(req), HttpStatus.OK);
    }

    @PostMapping(value = "/faq")
    public ResponseEntity<PageResultDTO<HelpDTO, Help>> getFAQList(@ModelAttribute("requestDTO") PageRequestDTO req) {
        log.info("PageRequest:" + req);
        return new ResponseEntity<>(helpService.getFAQList(req), HttpStatus.OK);
    }

    // token보내주기
    // Long타입으로 게시판 number 보내주기
    @PostMapping(value = "/read/{hbno}")
    public ResponseEntity<HelpDTO> read(@PathVariable("hbno") Long hbno) {
        log.info("read....num: " + hbno);
        return new ResponseEntity<>(helpService.read(hbno), HttpStatus.OK);
    }

    // private Long hbno;
    // private String title;
    // private String content;
    // private Long writer;
    // body = {bhno:long타입으로 게시글번호 보내주기 , title:string , content:string, writer:long
    // }
    @PostMapping(value = "/Nwrite")
    public ResponseEntity<Long> Noticeregister(@RequestBody HelpDTO dto) {
        log.info("Notice register... dto: " + dto);
        Long hbno = helpService.Noticeregister(dto);
        return new ResponseEntity<>(hbno, HttpStatus.OK);
    }

    @PostMapping(value = "/Fwrite")
    public ResponseEntity<Long> FAQregister(@RequestBody HelpDTO dto) {
        log.info("QnA register... dto: " + dto);
        Long hbno = helpService.FAQregister(dto);
        return new ResponseEntity<>(hbno, HttpStatus.OK);
    }

    @PostMapping(value = "/modify/{hbno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody HelpDTO dto) {
        log.info("modify... dto: " + dto);
        helpService.modifyHelp(dto);
        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{hbno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("hbno") HelpDTO dto) {// 프론트에 주는타입 ,,@ 주소에서 받는내용(쿼리스트링)
        log.info("remove......" + dto);
        helpService.deleteHelp(dto);
        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

}
