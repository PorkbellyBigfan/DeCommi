package org.zerock.decommi.dto;
import java.util.List;
<<<<<<< HEAD
import java.util.function.Function;
import java.util.stream.Collectors;
=======
>>>>>>> 2010378dacc4f8633f655f21789c4419a739af5b

import lombok.Data;

@Data
public class SearchResultDTO<DTO, EN> {
  private List<DTO> dtoList;

  public SearchResultDTO(List<EN> result, Function<EN,DTO> fn){
    dtoList = result.stream().map(fn).collect(Collectors.toList());
  }

}
