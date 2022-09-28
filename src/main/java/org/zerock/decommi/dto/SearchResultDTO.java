package org.zerock.decommi.dto;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class SearchResultDTO<DTO, EN> {
  private List<DTO> dtoList;

  public SearchResultDTO(List<EN> result, Function<EN,DTO> fn){
    dtoList = result.stream().map(fn).collect(Collectors.toList());
  }

}
