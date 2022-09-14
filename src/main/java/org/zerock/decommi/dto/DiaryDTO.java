package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private boolean openYN;
    private boolean commentYN;
    private int replyCnt;
    private int heartCnt;
    private int reportCnt;
    private int bookmarkCnt;

    private String writerEmail; // Member의 Primary key :email

    @Builder.Default
    private List<TagDTO> tagDTOList = new ArrayList<>();

    private LocalDateTime regDate, modDate;
}
