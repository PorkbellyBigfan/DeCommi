package org.zerock.decommi.dto;

import org.zerock.decommi.entity.diary.Diary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {
    private Long tagId;
    private String tagName;
    // private boolean isSubTag;
    // private Long tagGroup;
    private Diary dino;
}
