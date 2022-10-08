package org.zerock.decommi.service.diary;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.FileDTO;
import org.zerock.decommi.dto.HeartDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.dto.ReportDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.File;
import org.zerock.decommi.entity.diary.Heart;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.diary.Report;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Bookmark;
import org.zerock.decommi.entity.member.Member;

public interface DiaryService {
    // 다이어리
    String registerDiary(DiaryDTO dto);

    DiaryDTO checkBeforeDiaryModify(Long dino, String id);

    String modifyDiary(DiaryDTO dto, List<String> tagList);

    Boolean deleteDiary(DiaryDTO dto);

    DiaryDTO getDiaryPostByDino(Long dino);

    PageResultDTO<DiaryDTO, Diary> getDiaryPostList(PageRequestDTO requestDTO);

    // 결국 태그 검색 구현 못함
    // HashMap<String, Object> getSearchDiaryPostList(PageRequestDTO requestDTO);

    PageResultDTO<DiaryDTO, Diary> getDiaryPostListByTagName(PageRequestDTO requestDTO, String tagName);
    
    String registerReply(ReplyDTO dto);

    Long addNewReply(ReplyDTO dto); // 대댓글?

    String modifyReply(ReplyDTO dto);

    String deleteReply(ReplyDTO dto);

    HashMap<String, Object> getReplyListByDino(Long dino, Pageable pageable);

    // 북마크
    Boolean addBookmark(BookmarkDTO dto);

    // 하트
    Boolean addHeart(HeartDTO dto);

    // 신고
    Boolean addDiaryReport(ReportDTO dto);

    // 다이어리
    default Diary dtoToEntity(DiaryDTO dto) {
        Diary diary = Diary.builder()
                .dino(dto.getDino())
                .title(dto.getTitle())
                .content(dto.getContent())
                .openYN(dto.isOpenYN())
                .replyYN(dto.isReplyYN())
                .writer(dto.getWriter())
                .build();
        return diary;
    }

    // 다이어리
    default DiaryDTO entityToDTO(Diary diary) {
        DiaryDTO dto = DiaryDTO.builder()
                .dino(diary.getDino())
                .title(diary.getTitle())
                .content(diary.getContent())
                .openYN(diary.isOpenYN())
                .replyYN(diary.isReplyYN())
                .writer(diary.getWriter())
                .regDate(diary.getRegDate())
                .build();
        return dto;

    }

    // 파일
    default File fileDTOtoEntity(FileDTO dto, Long dino) {
        File file = File.builder()
                .uuid(dto.getUuid())
                .fname(dto.getImgName())
                .fpath(dto.getPath())
                .dino(Diary.builder().dino(dino).build())
                .build();
        return file;
    }

    // 태그
    default Tag tagDTOtoEntity(String tagList) {
        Tag tag = Tag.builder()
                .tagName(tagList)
                .build();
        return tag;
    }

    // 태그
    default TagDTO tagEntityToDTO(Tag tagList) {
        TagDTO dto = TagDTO.builder()
                .tagId(tagList.getTagId())
                .tagName(tagList.getTagName())
                .dino(tagList.getDino())
                .build();
        return dto;
    }

    // 댓글
    default Reply replyDTOtoEntity(ReplyDTO dto) {
        Diary diary = Diary.builder().dino(dto.getDino()).build();
        Member member = Member.builder().mid(dto.getMid()).build();
        Reply reply = Reply.builder()
                .rno(dto.getRno())
                .dino(diary)
                .member(member)
                .replyContent(dto.getReplyContent())
                .replyGroup(dto.getReplyGroup())
                .replyDepth(dto.getReplyDepth())
                .replyOrder(dto.getReplyOrder())
                .build();
        return reply;
    }

    // 댓글
    default ReplyDTO replyEntityToDTO(Reply reply) {
        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .dino(reply.getDino().getDino())
                .mid(reply.getMember().getMid())
                .replyContent(reply.getReplyContent())
                .replyGroup(reply.getReplyGroup())
                .replyDepth(reply.getReplyDepth())
                .replyOrder(reply.getReplyOrder())
                .regDate(reply.getRegDate())
                .build();
        return dto;
    }

    // 하트
    default Heart heartDTOtoEntity(HeartDTO dto) {
        Heart entity = Heart.builder()
                .hid(dto.getHid())
                .dino(dto.getDino())
                .mid(dto.getMid())
                .build();
        return entity;
    }

    // 하트
    default HeartDTO heartEntityToDTO(Heart entity) {
        HeartDTO dto = HeartDTO.builder()
                .hid(entity.getHid())
                .dino(entity.getDino())
                .mid(entity.getMid())
                .build();
        return dto;
    }

    // 북마크
    default Bookmark bookmarkDTOtoEntity(BookmarkDTO dto) {
        Bookmark entity = Bookmark.builder()
                .dino(dto.getDino())
                .bid(dto.getBid())
                .mid(dto.getMid())
                .build();
        return entity;
    }

    // 북마크
    default BookmarkDTO bookmarkEntityToDto(Bookmark entity) {
        BookmarkDTO dto = BookmarkDTO.builder()
                .dino(entity.getDino())
                .bid(entity.getBid())
                .mid(entity.getMid())
                .build();
        return dto;

    }

    // 신고
    default Report reportDTOtoEntity(ReportDTO dto) {
        Report entity = Report.builder().reid(dto.getReid())
                .dino(Diary.builder().dino(dto.getDino()).build())
                .mid(Member.builder().mid(dto.getMid()).build())
                .title(dto.getTitle())
                .reportContent(dto.getReportContent())
                .build();
        return entity;
    }

    // 신고
    default ReportDTO reportEntitytoDTO(Report entity) {
        ReportDTO dto = ReportDTO.builder().reid(entity.getReid())
                .dino(entity.getDino().getDino())
                .mid(entity.getMid().getMid())
                .title(entity.getTitle())
                .reportContent(entity.getReportContent())
                .build();
        return dto;
    }

}
