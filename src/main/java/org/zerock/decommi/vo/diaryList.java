package org.zerock.decommi.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class diaryList {
  private Long dino;
  private String title;
  private String content;
  private String writer;
  private Long heartCnt;
  private Long bookmarkCnt;
  private Long replyCnt;
  
}
