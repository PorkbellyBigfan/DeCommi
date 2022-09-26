package org.zerock.decommi.vo;

import java.util.List;

import lombok.Data;

@Data
public class SearchCondition {
  String search;
  String tag;

  public SearchCondition(String search, String tag) {
    this.search = search;
    this.tag = tag;
  }
}
