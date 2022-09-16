package org.zerock.decommi.service.diary;

import java.util.List;

import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Member;

public interface DiaryService {
    //다이어리
    String registerDiary(DiaryDTO dto, List<TagDTO> tagList);
    String modifyDiary(DiaryDTO dto, String email);
    String deleteDiary(Long dino, String email);
    List<Object[]>getDiaryList();
    List<Object[]>getSearchDiaryList(String search);


    //댓글
    String registerReply(ReplyDTO dto);
    String modifyReply(ReplyDTO dto, String email);
    String deleteReply(ReplyDTO dto, String email);

    // 북마크
    // 하트
    // 신고


    //다이어리
    default Diary dtoToEntity(DiaryDTO dto){
        Diary diary = Diary.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .openYN(dto.isOpenYN())
            .replyYN(dto.isReplyYN())
            .writer(dto.getWriter())
            .build();
        return diary;
    }
    //다이어리
    default DiaryDTO entityToDTO(Diary diary){
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
    //태그
    default Tag tagDTOtoEntity(TagDTO tagList){
        Tag tags = Tag.builder()
            .tagName(tagList.getTagName())
            .tagGroup(tagList.getTagGroup())
            .isSubTag(tagList.isSubTag())
            .dino(tagList.getDino())
            .build();
        return tags;
    }
    //태그
    default TagDTO tagEntityToDTO(Tag tagList){
        TagDTO dto = TagDTO.builder()
            .tagId(tagList.getTagId())
            .tagName(tagList.getTagName())
            .tagGroup(tagList.getTagGroup())
            .isSubTag(tagList.isSubTag())
            .dino(tagList.getDino())
            .build();
        return dto;
    }

    //댓글
    default Reply replyDTOtoEntity(ReplyDTO dto){
        Diary diary = Diary.builder().dino(dto.getDino()).build();
        Member member = Member.builder().email(dto.getEmail()).build();
        Reply reply = Reply.builder()
            .rno(dto.getRno())
            .diary(diary)
            .member(member)
            .replyContent(dto.getReplyContent())
            .replyGroup(dto.getReplyGroup())
            .replyDepth(dto.getReplyDepth())
            .replyOrder(dto.getReplyOrder())
            .build();
        return reply;
    }
    //댓글
    default ReplyDTO replyEntityToDTO(Reply reply){
        ReplyDTO dto = ReplyDTO.builder()
            .rno(reply.getRno())
            .dino(reply.getDiary().getDino())
            .email(reply.getMember().getEmail())
            .replyContent(reply.getReplyContent())
            .replyGroup(reply.getReplyGroup())
            .replyDepth(reply.getReplyDepth())
            .replyOrder(reply.getReplyOrder())
            .regDate(reply.getRegDate())
            .build();
        return dto;
    }

}
