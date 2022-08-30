package org.zerock.decommi.dto;

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

    private int replyClass;
    private int replyOrder;
    private int replyGroup;

    private String writerEmail; // Member 엔티티의 PK email
    private Long diarynum; // Diary 엔티티의 PK dino
}
