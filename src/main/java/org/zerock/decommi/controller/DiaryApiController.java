package org.zerock.decommi.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.HeartDTO;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.dto.ReportDTO;
import org.zerock.decommi.dto.UploadResultDTO;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.vo.Reply2;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@RequestMapping("/api/diary")
@Log4j2
@RequiredArgsConstructor
public class DiaryApiController {
    private final DiaryService diaryService;

    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @RequestMapping(value = "/write", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody DiaryDTO dto) {
        List<String> tagList = dto.getTagList();
        return new ResponseEntity<>(diaryService.registerDiary(dto), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/write/image")
    public Map<String, Object> uploadImage(@RequestParam Map<String, Object> paramMap, MultipartRequest request)
            throws Exception {
        MultipartFile uploadFile = request.getFile("upload");
        // windows 일경우 이 경로 사용
        String uploadDir = "c:\\testingimage\\";
        // String uploadDir = "/Users/hyunseokbyun/Documents/Imagefiles/";
        String uploadId = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(uploadFile.getOriginalFilename());
        uploadFile.transferTo(new File(uploadDir + uploadId));
        paramMap.put("url", "/decommi/diary/image/" + uploadId);
        log.info("ckckckkkkkkkkkkkkkkkkkkkk" + paramMap);
        return paramMap;
    }

    @RequestMapping(value = "/modify/check", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiaryDTO> CheckBeforeDiaryModify(@RequestBody DiaryDTO dto) {
        DiaryDTO diaryPost = diaryService.checkBeforeDiaryModify(dto.getDino(), dto.getWriter());
        return new ResponseEntity<>(diaryPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/modify/register", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> articleModify(@RequestBody DiaryDTO dto) {
        String diaryPost = diaryService.modifyDiary(dto, dto.getTagList());
        return new ResponseEntity<>(diaryPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> deleteDiary(@RequestBody DiaryDTO dto) {
        log.info("controller ::: delete ::::: "+dto);
        return new ResponseEntity<>(diaryService.deleteDiary(dto), HttpStatus.OK);
    }

    @PostMapping("/write/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        List<UploadResultDTO> result = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String orig = uploadFile.getOriginalFilename();
            String fileName = orig.substring(orig.lastIndexOf("\\") + 1);
            log.info("Original File Name: " + orig);
            log.info("File Name: " + fileName);

            String folderPath = makeFolder();

            String uuid = UUID.randomUUID().toString();
            String saveName = uploadPath + File.separator + folderPath + File.separator
                    + uuid + "_" + fileName;
            log.info(saveName);
            Path savePath = Paths.get(saveName);
            try {
                uploadFile.transferTo(savePath);
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
                        + "s_" + uuid + "_" + fileName;
                File thumbnailFile = new File(thumbnailSaveName);
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

                result.add(new UploadResultDTO(fileName, uuid, folderPath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    // 하트
    @RequestMapping(value = "/heart", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> HeartDiary(@RequestBody HeartDTO dto) {
        return new ResponseEntity<>(diaryService.addHeart(dto), HttpStatus.OK);
    }

    // 북마크
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> bookmarDiary(@RequestBody BookmarkDTO dto) {
        return new ResponseEntity<>(diaryService.addBookmark(dto), HttpStatus.OK);
    }

    // 신고
    @RequestMapping(value = "/report", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> reportingDiary(@RequestBody ReportDTO dto) {
        return new ResponseEntity<>(diaryService.addDiaryReport(dto), HttpStatus.OK);
    }

    // 댓글페이지
    @RequestMapping(value = "/reply/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HashMap<String, Object>> replyRead(@RequestBody Reply2 reply2) {
        Pageable pageable = PageRequest.of(reply2.getReqPage(), 5);
        HashMap<String, Object> result = diaryService.getReplyListByDino(reply2.getDino(), pageable);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 댓글등록
    @RequestMapping(value = "/reply/add/", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> registerReply(@RequestBody ReplyDTO dto) {
        String result = diaryService.registerReply(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 대댓글
    @RequestMapping(value = "/reply/add/reply", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addReply(@RequestBody ReplyDTO dto) {
        Long result = diaryService.addNewReply(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 댓글삭제
    @RequestMapping(value = "/reply/remove", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteReply(@RequestBody ReplyDTO dto) {
        return new ResponseEntity<>(diaryService.deleteReply(dto), HttpStatus.OK);
    }

    // 댓글수정
    @RequestMapping(value = "/reply/modify", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modifyReply(@RequestBody ReplyDTO dto) {
        String result = diaryService.modifyReply(dto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}