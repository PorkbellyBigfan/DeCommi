package org.zerock.decommi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Help2DTO {
    private Long hbno;
    private String title;
    private String content;
    private Long writer;
    private String helpType;
    private String answer;

}
