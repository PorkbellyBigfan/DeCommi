// package org.zerock.decommi.vo;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.zerock.decommi.entity.diary.File;
// import org.zerock.decommi.entity.diary.Reply;
// import org.zerock.decommi.repository.diary.DiaryRepository;

// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Data
// @AllArgsConstructor
// public class DiaryPost {
//     private Long dino;
//     private String writer;
//     private String title;
//     private String content;
//     private boolean openYN;
//     private boolean replyYN;
//     private List<String> tagList;
//     private List<Reply> replyList;
//     private List<File> files;
//     // private Long replyCnt;
//     // private Long heartCnt;
//     // private Long bookmarkCnt;
//     // private Long reportCnt;
//     // private LocalDateTime regDate, modDate;

//     public DiaryPost(DiaryRepository.getDiaryPost dp) {
//         this.dino = dp.getDino();
//         this.writer = dp.getWriter();
//         this.title = dp.getTitle();
//         this.content = dp.getContent();
//         this.openYN = dp.isOpenYN();
//         this.replyYN = dp.isReplyYN();
//         this.tagList = dp.getTagList();
//         this.replyList = dp.getReplyList();
//         this.files = dp.getFiles();
//         // this.replyCnt = dp.getReplyCnt();
//         // this.heartCnt = dp.getHeartCnt();
//         // this.bookmarkCnt = dp.getBookmarkCnt();
//         // this.reportCnt = dp.getReportCnt();
//         // this.regDate = dp.getRegDate();
//         // this.modDate = dp.getModDate();
//     }

// }
