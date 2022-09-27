package org.zerock.decommi.dto;

import java.util.HashSet;
import java.util.Set;

import org.zerock.decommi.entity.HelpRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelpDTO {
    private Long hbno;
    private String title;
    private String content;
    private Long writer;

    @Builder.Default
    private Set<String> roleSet = new HashSet<>();
  

}
