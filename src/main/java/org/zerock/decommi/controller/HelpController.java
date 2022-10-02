package org.zerock.decommi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping(value = "/Notice")
    public ResponseEntity<PageResultDTO<HelpDTO,Help>> Noticelist(@ModelAttribute("requestDTO") PageRequestDTO req) {
        log.info("PageRequest:" + req);
        return new ResponseEntity<>(helpService.getNoticeList(req), HttpStatus.OK);
    }

    @GetMapping(value = "/FAQ")
    public ResponseEntity<PageResultDTO<HelpDTO,Help>> QnAlist(@ModelAttribute("requestDTO") PageRequestDTO req) {
        log.info("PageRequest:" + req);
        return new ResponseEntity<>(helpService.getQnAList(req), HttpStatus.OK);
    }

    @GetMapping(value = "/read/{hbno}")
    public ResponseEntity<HelpDTO> read(@PathVariable("hbno") Long hbno) {
        log.info("read....num: " + hbno);
        return new ResponseEntity<>(helpService.read(hbno), HttpStatus.OK);
    }

    @PostMapping(value = "/write")
    public ResponseEntity<Long> register(@RequestBody HelpDTO dto) {
        log.info("register... dto: " + dto);
        Long hbno = helpService.register(dto);
        return new ResponseEntity<>(hbno, HttpStatus.OK);
    }

    @PostMapping(value = "/modify/{hbno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody HelpDTO dto){
        log.info("modify... dto: " + dto);
        helpService.modifyHelp(dto);
        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{hbno}",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("hbno") Long hbno){//프론트에 주는타입 ,,@ 주소에서 받는내용(쿼리스트링)
        log.info("remove......"+hbno);
        helpService.deleteHelp(hbno);
        return new ResponseEntity<>("removed",HttpStatus.OK);
    }
    
}
