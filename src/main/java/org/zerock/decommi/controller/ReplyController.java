// package org.zerock.decommi.controller;

// import java.util.List;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.zerock.decommi.dto.ReplyDTO;
// import org.zerock.decommi.service.reply.ReplyService;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @RestController
// @RequestMapping("replies")
// @Log4j2
// @RequiredArgsConstructor
// public class ReplyController {
// private final ReplyService service;

// @GetMapping(value = "/diary/{dino}", produces =
// MediaType.APPLICATION_JSON_VALUE)
// public ResponseEntity<List<ReplyDTO>> getListByDiary(@PathVariable("dino")
// Long dino) {
// log.info("dino:" + dino);
// return new ResponseEntity<>(service.getList(dino), HttpStatus.OK);
// }

// @PostMapping("")
// public ResponseEntity<Long> register(@RequestBody ReplyDTO dto) {
// return new ResponseEntity<>(service.register(dto), HttpStatus.OK);
// }

// @PutMapping("")
// public ResponseEntity<String> modify(@RequestBody ReplyDTO dto) {
// service.modify(dto);
// return new ResponseEntity<>("success", HttpStatus.OK);
// }

// @DeleteMapping("/{rno}")
// public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
// service.remove(rno);
// return new ResponseEntity<>("success", HttpStatus.OK);
// }
// }
