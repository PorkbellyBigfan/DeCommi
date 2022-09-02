package org.zerock.decommi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.entity.Diary;
import org.zerock.decommi.entity.Reply;
import org.zerock.decommi.repository.DiaryRepository;
import org.zerock.decommi.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository repository;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        repository.save(reply).getRno();
        return reply.getRno();
        // return repository.save(dtoToEntity(replyDTO)).getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long dino) {
        List<Reply> result = repository.getRepliesByDiaryOrderByRno(
                Diary.builder().dino(dino).build());
        return result.stream().map(reply -> entityToDTO(reply))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        repository.save(dtoToEntity(replyDTO));
    }

    @Override
    public void remove(Long rno) {
        repository.deleteById(rno);
    }
}
