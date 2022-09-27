package org.zerock.decommi.vo;

import java.time.LocalDateTime;
import java.util.List;

import org.zerock.decommi.repository.diary.DiaryRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiaryPostList {
  private Long dino;
  private String writer;
  private String title;
  private String content;
  private List<String> tagList;
  private Long replyCnt;
  private Long heartCnt;
  private Long bookmarkCnt;
  private Long reportCnt;
  private LocalDateTime regDate;

  public DiaryPostList(DiaryRepository.getDiaryPostList dpl) {
    this.dino = dpl.getDino();
    this.writer = dpl.getWriter();
    this.title = dpl.getTitle();
    this.content = dpl.getContent();
    this.tagList = dpl.getTagList();
    this.replyCnt = dpl.getReplyCnt();
    this.heartCnt = dpl.getHeartCnt();
    this.bookmarkCnt = dpl.getBookmarkCnt();
    this.reportCnt = dpl.getReportCnt();
    this.regDate = dpl.getRegDate();
  }
}
