package org.zerock.decommi.controller;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.entity.diary.Heart;

import org.zerock.decommi.service.diary.HeartService;
import org.zerock.decommi.vo.Mid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class HeartController {
    private final HeartService service;

    @RequestMapping(value = "/heart", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> register(@RequestBody Mid mid) {
        HashMap<String, Object> result = service.getListDino(mid.getMid());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
