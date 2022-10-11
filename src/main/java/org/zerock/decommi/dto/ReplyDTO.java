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
    private Long mid; // Member id
    private Long dino; // Diary dino

    private String replyContent;
    private Long replyGroup;
    private Long replyDepth;
    private Long replyOrder;

    private LocalDateTime regDate, modDate;
}
