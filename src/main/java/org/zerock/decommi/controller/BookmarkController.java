package org.zerock.decommi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.service.diary.BookmarkService;
import org.zerock.decommi.vo.Mid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/asd")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService service;

    @RequestMapping(value = "/folderlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookmarkDTO>> folderList(@RequestBody Mid mid){
        return new ResponseEntity<>(service.getBookFolderList(mid.getMid()),HttpStatus.OK);
    }

    @RequestMapping(value = "/bookmark", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookmarkDTO>> register(@RequestBody Mid mid) {
        return new ResponseEntity<>(service.getListDino(mid.getMid(), mid.getBfolderName()), HttpStatus.OK);
    }
}
