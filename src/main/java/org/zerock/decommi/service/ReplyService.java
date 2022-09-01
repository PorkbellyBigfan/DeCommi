// package org.zerock.decommi.service;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.zerock.decommi.dto.ReplyDTO;
// import org.zerock.decommi.dto.ReplyDTO.ReplyDTOBuilder;
// import org.zerock.decommi.entity.Diary;
// import org.zerock.decommi.entity.Member;
// import org.zerock.decommi.entity.Reply;

// public interface ReplyService {
// Long register(ReplyDTO replyDTO);

// List<ReplyDTO> getList(Long dino);

// void modify(ReplyDTO replyDTO);

// void remove(Long rno);

<<<<<<< HEAD
  default Reply dtoToEntity(ReplyDTO dto) {
    Diary diary = Diary.builder().dino(dto.getDino()).build();
=======
// default Reply dtoToEntity(ReplyDTO dto) {

// Diary diary = Diary.builder().dino(dto.getDino()).build();
>>>>>>> 25dce0b61b859f0b329cd8b04ee925ce74edc4c3

// Reply reply = Reply.builder().rno(dto.getRno())
// .replyContent(dto.getReplyContent())
// .writer(Member.builder().email(dto.getWriterEmail()).build())
// .diary(diary)
// .build();
// return reply;
// }

// default ReplyDTO entityToDTO(Reply reply) {

// ReplyDTO replyDTO = ReplyDTO.builder().rno(reply.getRno())
// .replyContent(reply.getReplyContent())
// .writerEmail(reply.getWriter().getEmail())
// .regDate(reply.getRegDate())
// .modDate(reply.getModDate())
// .build();
// return replyDTO;
// }

// }
