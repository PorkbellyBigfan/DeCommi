package org.zerock.decommi.dto;

import java.time.LocalDateTime;

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
    private boolean commentYN;
    private int reportCnt;
    private int heartCnt;
    private int bookmarkCnt;

    private String writerEmail; // Member의 Primary key :email

    private LocalDateTime regDate, modDate;
}
