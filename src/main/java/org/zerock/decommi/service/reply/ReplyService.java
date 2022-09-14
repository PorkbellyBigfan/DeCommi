package org.zerock.decommi.service.reply;

import java.util.List;

import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.member.Member;

public interface ReplyService {
  Long register(ReplyDTO replyDTO);

  List<ReplyDTO> getReplyList(Long dino);

  void modify(ReplyDTO replyDTO);

  void remove(Long rno);

  default Reply dtoToEntity(ReplyDTO dto) {
    Reply reply = Reply.builder()
        .rno(dto.getRno())
        .replyContent(dto.getReplyContent())
        .replyGroup(dto.getReplyGroup())
        .replyDepth(dto.getReplyDepth())
        .writer(Member.builder().email(dto.getWriter()).build())
        .dino(Diary.builder().dino(dto.getDino()).build())
        .build();
    return reply;
  }

  default ReplyDTO entityToDTO(Reply reply) {
    ReplyDTO dto = ReplyDTO.builder()
        .rno(reply.getRno())
        .replyContent(reply.getReplyContent())
        .replyGroup(reply.getReplyGroup())
        .replyDepth(reply.getReplyGroup())
        .writer(reply.getWriter().getEmail())
        .dino(reply.getDino().getDino())
        .regDate(reply.getRegDate())
        .modDate(reply.getModDate())
        .build();
    return dto;
  }

}
