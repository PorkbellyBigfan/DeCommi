package org.zerock.decommi.vo;

import java.time.LocalDateTime;
import java.util.List;

public class diarypost {
  Long dino;
  String writer;
  String title;
  String content;
  List<String> tagList;
  LocalDateTime regDate,modDate;
  boolean openYN;
  boolean replyYN;

}
