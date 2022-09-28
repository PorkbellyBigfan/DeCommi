package org.zerock.decommi.dto;
import lombok.Data;

@Data
public class SearchResultDTO<DTO, EN> {
  private List<DTO> dtoList;

}
