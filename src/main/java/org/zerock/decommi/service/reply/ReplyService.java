package org.zerock.decommi.service.reply;

import java.util.List;

import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.member.Member;

public interface ReplyService {
    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long dino);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    default Reply dtoToEntity(ReplyDTO dto) {

        Diary diary = Diary.builder().dino(dto.getDino()).build();

        Reply reply = Reply.builder().rno(dto.getRno())
                .replyContent(dto.getReplyContent())
                .writer(Member.builder().email(dto.getWriterEmail()).build())
                .diary(diary)
                .build();
        return reply;
    }

    default ReplyDTO entityToDTO(Reply reply) {

        ReplyDTO replyDTO = ReplyDTO.builder().rno(reply.getRno())
                .replyContent(reply.getReplyContent())
                .writerEmail(reply.getWriter().getEmail())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();
        return replyDTO;
    }

}
