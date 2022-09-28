package org.zerock.decommi.vo;

import java.util.List;

import lombok.Data;

@Data
public class SearchCondition {
  String title;
  String keyword;
  boolean tagOption;
  List<String> tagList;
  
  public SearchCondition(String title,String keyword, boolean tagOptipon, List<String>tagList){
    this.keyword = keyword;
    this.tagOption = tagOptipon;
    this.tagList = tagList;
    this.title = title;
  }
}
