package org.zerock.decommi.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.decommi.admin.dto.PageRequestDTO;
import org.zerock.decommi.admin.dto.PageResultDTO;
import org.zerock.decommi.admin.service.AdminService;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.ReportDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Report;
import org.zerock.decommi.entity.member.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequestMapping("/api/admin/")
@RestController
@RequiredArgsConstructor
@Log4j2
public class AdminController {

    private final AdminService adService;

    // 다이어리 리스트
    @RequestMapping(value = "/diarymanagement", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<DiaryDTO, Diary>> diarymanage(@RequestBody PageRequestDTO dto,
            @RequestHeader(value = "mid") Long mid) {
        if (permissionCheckingEverytime(mid)) {
            PageResultDTO<DiaryDTO, Diary> result = adService.getDiaryListWithTagAndReply(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 다이어리 삭제
    @RequestMapping(value = "/diarymanagement/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> diaryDelete(@RequestBody DiaryDTO dto,
            @RequestHeader(value = "mid") Long mid) {
        if (permissionCheckingEverytime(mid)) {
            return new ResponseEntity<>(adService.diaryDelete(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 태그 리스트
    // @RequestMapping(value = "/tagmanagement", method = RequestMethod.POST,
    // consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<String>> getTag(@RequestBody TagDTO dto,
    // @RequestHeader(value = "tagId") Long tagId) {
    // if (permissionCheckingEverytime(tagId)) {
    // return new ResponseEntity<>(adService.getTag(dto.getTagId()), HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    // }
    // }

    // 태그 수정
    @RequestMapping(value = "/tagmanagement/modify", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> tagModify(@RequestBody TagDTO dto,
            @RequestHeader(value = "tagId") Long tagId) {
        if (permissionCheckingEverytime(tagId)) {
            return new ResponseEntity<>(adService.tagModify(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 태그 삭제
    @RequestMapping(value = "/tagmanagement/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> tagdelete(@RequestBody TagDTO dto,
            @RequestHeader(value = "tagId") Long tagId) {
        if (permissionCheckingEverytime(tagId)) {
            return new ResponseEntity<>(adService.tagDelete(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원 리스트
    @RequestMapping(value = "/usermanagement", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<MemberDTO, Member>> usermanage(@RequestBody PageRequestDTO dto,
            @RequestHeader(value = "mid") Long mid) {
        if (permissionCheckingEverytime(mid)) {
            PageResultDTO<MemberDTO, Member> result = adService.getUserList(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원 수정
    @RequestMapping(value = "/usermanagement/modify", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> usermodify(@RequestBody MemberDTO dto,
            @RequestHeader(value = "mid") Long mid) {
        if (permissionCheckingEverytime(mid)) {
            return new ResponseEntity<>(adService.UserModifier(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 회원 삭제
    @RequestMapping(value = "/usermanagement/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> userdelete(@RequestBody MemberDTO dto,
            @RequestHeader(value = "mid") Long mid) {
        if (permissionCheckingEverytime(mid)) {
            return new ResponseEntity<>(adService.UserDeleter(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // 신고 리스트
    @RequestMapping(value = "/reportmanagement", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO<ReportDTO, Report>> reportmanage(@RequestBody PageRequestDTO dto,
            @RequestHeader(value = "mid") Long mid) {
        if (permissionCheckingEverytime(mid)) {
            PageResultDTO<ReportDTO, Report> result = adService.getReportList(dto);
            log.info(result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 신고 글 삭제
    @RequestMapping(value = "/reportmanagement/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> reportdelete(@RequestBody ReportDTO dto,
            @RequestHeader(value = "reid") Long reid) {
        if (permissionCheckingEverytime(reid)) {
            return new ResponseEntity<>(adService.reportDelete(dto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    public Boolean permissionCheckingEverytime(Long mid) {
        if (!adService.adminChecker(mid)) {
            return false;
        } else {
            return true;
        }
    }
}
