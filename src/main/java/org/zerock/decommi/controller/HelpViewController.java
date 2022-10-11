package org.zerock.decommi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.Help;
import org.zerock.decommi.service.HelpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/help")
public class HelpViewController {
    private final HelpService helpService;

    // token보내주기
    // body ={page ,keyword(검색시 필요?)}
    @PostMapping(value = "/notice")
    public ResponseEntity<PageResultDTO<HelpDTO, Help>> getNoticeList(@RequestBody PageRequestDTO req) {
        log.info("PageRequest:" + req);
        return new ResponseEntity<>(helpService.getNoticeList(req), HttpStatus.OK);
    }

    // token보내주기
    // body ={page ,keyword(검색시 필요?)}
    @PostMapping(value = "/faq")
    public ResponseEntity<PageResultDTO<HelpDTO, Help>> getFAQList(@RequestBody PageRequestDTO req) {
        log.info("PageRequest:" + req);
        return new ResponseEntity<>(helpService.getFAQList(req), HttpStatus.OK);
    }

    // hbno 게시판번호
    // body = {Long:hbno }
    @PostMapping(value = "/read/{hbno}")
    public ResponseEntity<HelpDTO> read(@PathVariable("hbno") Long hbno) {
        log.info("read....num: " + hbno);
        return new ResponseEntity<>(helpService.read(hbno), HttpStatus.OK);
    }
}
