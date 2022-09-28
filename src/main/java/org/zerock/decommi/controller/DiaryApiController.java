package org.zerock.decommi.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.HeartDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.ReportDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.dto.UploadResultDTO;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.service.member.MemberService;
import org.zerock.decommi.vo.DiaryPost;

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
        log.info("controller dto.getTagList() : " + dto.getTagList());
        log.info("controller tagList : "+ tagList);
        log.info("dto : " + dto);
        return new ResponseEntity<>(diaryService.registerDiary(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/modify/check", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiaryDTO> CheckBeforeDiaryModify(@RequestBody DiaryPost vo) {
        DiaryDTO diaryPost = diaryService.checkBeforeDiaryModify(vo.getDino(), vo.getWriter());
        return new ResponseEntity<>(diaryPost, HttpStatus.OK);
    }

    @RequestMapping(value = "/modify/register", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> articleModify(@RequestBody DiaryDTO dto) {
        String diaryPost = diaryService.modifyDiary(dto, dto.getTagList());
        return new ResponseEntity<>(diaryPost, HttpStatus.OK);
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

    @RequestMapping(value = "/heart", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> HeartDiary(@RequestBody HeartDTO dto) {
        return new ResponseEntity<>(diaryService.addHeart(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> bookmarDiary(@RequestBody BookmarkDTO dto) {
        return new ResponseEntity<>(diaryService.addBookmark(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> reportingDiary(@RequestBody ReportDTO dto) {
        return new ResponseEntity<>(diaryService.addDiaryReport(dto), HttpStatus.OK);
    }

}
