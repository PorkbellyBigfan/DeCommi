package org.zerock.decommi.service.reply;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
  private final ReplyRepository repository;

  @Override
  public Long register(ReplyDTO dto) {
    return repository.save(dtoToEntity(dto)).getRno();
  }

  @Override
  public List<ReplyDTO> getReplyList(Long dino) {
    List<Reply> result = repository.findByDino(dino);
    return result.stream().map(new Function<Reply, ReplyDTO>() {
      @Override
      public ReplyDTO apply(Reply t) {
        return entityToDTO(t);
      }
    }).collect(Collectors.toList());
  }

  @Override
  public void modify(ReplyDTO dto) {
    Long rno = dto.getRno();
    Optional<Reply> result = repository.findById(rno);
    if (result.isPresent()) {
      Reply reply = result.get();
      reply.changeReplyContent(dto.getReplyContent());
      repository.save(reply);
    }
  }

  @Override
  public void remove(Long rno) {
    repository.deleteById(rno);
  }
}
