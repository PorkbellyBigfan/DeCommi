package org.zerock.decommi.dto;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO { // which page, 어떤 페이지를 요청
  private int page;
  private int size;
  private String type;
  private String sort;
  private String keyword;
  private String writer; // (마이 다이어리 리스트)를 위해서 본인인지 확인하려고 컨트롤러에 DiaryDTO에 담겨있는 id 와 매칭검사할 예정
  private List<String> tagList;

  public PageRequestDTO() {
    page = 1;
    size = 5;
  }

  public Pageable getPageable(Sort sort) {
    return PageRequest.of(page - 1, size, sort);
  }
}
