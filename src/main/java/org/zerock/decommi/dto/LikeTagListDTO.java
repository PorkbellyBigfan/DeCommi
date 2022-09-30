package org.zerock.decommi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeTagListDTO {
  private Long lid;
  private String likeTagName;
  private Long mid;
  private Long tagId;
}
