package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.zerock.decommi.entity.diary.Reply;

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
    private List<TagDTO> tags;
    private List<Reply> replyList;
    private boolean openYN;
    private boolean replyYN;

    private String writer; // Member의 Primary key :id

    private LocalDateTime regDate, modDate;
}
