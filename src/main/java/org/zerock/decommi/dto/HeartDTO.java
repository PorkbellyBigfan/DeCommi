package org.zerock.decommi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeartDTO {
    private Long heartId;
    private boolean isHeart;

    private Long dino; // Diary 엔티티의 PK dino
    private String email; // Member 엔티티의 PK email
}
