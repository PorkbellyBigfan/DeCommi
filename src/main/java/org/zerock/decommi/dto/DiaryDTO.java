package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.diary.Tag;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    @Builder.Default
    private List<FileDTO> fileDTOList = new ArrayList<>();

    private List<String> tags;

    private List<Reply> replyList;

    private LocalDateTime regDate, modDate;
}
