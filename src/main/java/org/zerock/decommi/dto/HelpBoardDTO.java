package org.zerock.decommi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelpBoardDTO {
    private int hbno;
    private String title;
    private String content;

    private Enum hbType[];

    private String memberemail;

}
