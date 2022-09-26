package org.zerock.decommi.vo;

import java.time.LocalDateTime;

import org.zerock.decommi.repository.diary.DiaryRepository;

import lombok.Data;

@Data
public class DiaryPost {
  private Long dino;
  private String writer;
  private String title;
  private String content;
  private Long replyCnt;
  private Long heartCnt;
  private Long bookmarkCnt;
  private Long reportCnt;
  private LocalDateTime regDate, modDate;

  public DiaryPost(DiaryRepository.getDiaryPost dp) {
    this.dino = dp.getDino();
    this.writer = dp.getWriter();
    this.title = dp.getTitle();
    this.content = dp.getContent();
    this.replyCnt = dp.getReplyCnt();
    this.heartCnt = dp.getHeartCnt();
    this.bookmarkCnt = dp.getBookmarkCnt();
    this.reportCnt = dp.getReportCnt();
    this.regDate = dp.getRegDate();
    this.modDate = dp.getModDate();
  }

}
