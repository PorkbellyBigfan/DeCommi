package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.zerock.decommi.entity.member.Member;

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

    // private int replyClass;
    // private int replyOrder;
    // private int replyGroup;

    private String writerEmail; // Member의 Primary key :email
    private Long dino; // Diary 엔티티의 PK dino
    // @Builder.Default
    // private List<TagDTO> dairyDTOList = new ArrayList<>();

    private LocalDateTime regDate, modDate;
}
