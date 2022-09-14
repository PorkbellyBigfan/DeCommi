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
public class ReplyDTO {
    private Long rno;
    private String replyContent;

    private int replyGroup;
    private int replyDepth;

    private Long dino; // Diary 엔티티의 PK dino
    private String writer; // Member의 Primary key :email

    private LocalDateTime regDate, modDate;
}
