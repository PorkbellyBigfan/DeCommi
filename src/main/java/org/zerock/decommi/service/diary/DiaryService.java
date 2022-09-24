package org.zerock.decommi.service.diary;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.FileDTO;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.File;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Member;

public interface DiaryService {
    // 다이어리
    String registerDiary(DiaryDTO dto, List<TagDTO> tagList);

    DiaryDTO checkBeforeDiaryModify(Long dino, String id);

    String modifyDiary(DiaryDTO dto, List<TagDTO> tagList);

    void deleteDiary(Long dino);

    List<Object[]> getDiaryList();

    // 검색조건 만족하는 다이어리 게시글 리스트
    // List<Object[]> getSearchDiaryList(String search);

    // 좋아요태그포함된 다이어리 게시글 리스트
    // List<Object[]> getLikeTagDiaryList();

    // // 댓글
    // Long registerReply(ReplyDTO dto);
    // String modifyReply(ReplyDTO dto, String id);
    // String deleteReply(ReplyDTO dto, String id);
    // HashMap<String, Object> getReplyListByDino(Long dino, Pageable pageable);
    // HashMap<String, Object> getReplyListByDinoWithId(Long dino, Pageable
    // pageable, String id);

    // 북마크
    // 하트
    // 신고

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
    default Tag tagDTOtoEntity(TagDTO dto) {
        Tag tag = Tag.builder()
                .tagName(dto.getTagName())
                .tagGroup(dto.getTagGroup())
                .isSubTag(dto.isSubTag())
                .dino(dto.getDino())
                .build();
        return tag;
    }

    // 태그
    default TagDTO tagEntityToDTO(Tag tagList) {
        TagDTO dto = TagDTO.builder()
                .tagId(tagList.getTagId())
                .tagName(tagList.getTagName())
                .tagGroup(tagList.getTagGroup())
                .isSubTag(tagList.isSubTag())
                .dino(tagList.getDino())
                .build();
        return dto;
    }

    // 댓글
    default Reply replyDTOtoEntity(ReplyDTO dto) {
        Diary diary = Diary.builder().dino(dto.getDino()).build();
        Member member = Member.builder().id(dto.getId()).build();
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
                .id(reply.getMember().getId())
                .replyContent(reply.getReplyContent())
                .replyGroup(reply.getReplyGroup())
                .replyDepth(reply.getReplyDepth())
                .replyOrder(reply.getReplyOrder())
                .regDate(reply.getRegDate())
                .build();
        return dto;
    }

}
