package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.List;

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
    private List<String>tags;
    private boolean openYN;
    private boolean replyYN;
    // private int replyCnt;
    // private int heartCnt;
    // private int reportCnt;
    // private int bookmarkCnt;

    private String writer; // Member의 Primary key :email

    private LocalDateTime regDate, modDate;
}
