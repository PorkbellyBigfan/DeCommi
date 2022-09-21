package org.zerock.decommi.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ApiSessionDTO {
  private Long mid;
  private String email;
  private String id;
  private String token;
  private String curl;
  private boolean fromSocial;
  private Map<String, Object> attr; // OAuth from Social
}
