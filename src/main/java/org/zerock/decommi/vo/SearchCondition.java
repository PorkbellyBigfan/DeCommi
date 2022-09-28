package org.zerock.decommi.vo;

import java.util.List;

import lombok.Data;

@Data
public class SearchCondition {
  String search;
  List<String> tagList;
  
  public SearchCondition(String search, List<String>tagList){
    this.search = search;
    this.tagList = tagList;
  }
}
