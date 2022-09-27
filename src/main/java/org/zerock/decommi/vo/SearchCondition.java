package org.zerock.decommi.vo;

import java.util.List;

import lombok.Data;

@Data
public class SearchCondition {
  String search;
  String type;
  boolean searchType; // 0 일때는 모든 태그 포함된 게시글만 검색, 1일때는 태그가 하나라도 포함된 게시글 전부 검색
  List<String> tagList;

  public SearchCondition(String search, String type) {
    this.search = search;
    this.type = type;
  }
  public SearchCondition(String search, boolean searchType, List<String>tagList){
    this.search = search;
    this.searchType = searchType;
    this.tagList = tagList;
  }
}
