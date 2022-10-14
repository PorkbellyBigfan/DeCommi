package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.zerock.decommi.entity.diary.Tag;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDTO {
    private Long dino;
    private String title;

    private String content;
    private boolean openYN;
    private boolean replyYN;
    private String writer; // Member의 Primary key :id
    private Long replyCnt;
    private Long heartCnt;
    private Long bookmarkCnt;
    private Long reportCnt;

    @Builder.Default
    private List<FileDTO> fileDTOList = new ArrayList<>();

    private List<String> tagList;

    private List<ReplyDTO> replyList;

    private LocalDateTime regDate, modDate;
}
