package org.zerock.decommi.vo;

import java.util.List;

import org.zerock.decommi.entity.diary.File;
import org.zerock.decommi.entity.diary.Reply;
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
    private boolean openYN;
    private boolean replyYN;
    // private Long replyCnt;
    // private Long heartCnt;
    // private Long bookmarkCnt;
    // private Long reportCnt;
    private List<String> tagList;
    private List<Reply> replyList;
    private List<File> files;

    public DiaryPostList(DiaryRepository.getDiaryPostList dpl) {
        this.dino = dpl.getDino();
        this.writer = dpl.getWriter();
        this.title = dpl.getTitle();
        this.content = dpl.getContent();
        this.openYN = dpl.isOpenYN();
        this.replyYN = dpl.isReplyYN();
        this.tagList = dpl.getTagList();
        this.replyList = dpl.getReplyList();
        this.files = dpl.getFiles();

        // this.replyCnt = dpl.getReplyCnt();
        // this.heartCnt = dpl.getHeartCnt();
        // this.bookmarkCnt = dpl.getBookmarkCnt();
        // this.reportCnt = dpl.getReportCnt();
    }
}
