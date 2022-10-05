package org.zerock.decommi.service.diary;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.diary.QDiary;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.TagRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyDiaryServiceImpl implements MyDiaryService {
  private final DiaryRepository repository;
  private final TagRepository tagRepository;
  private final DiaryService diaryService;

  // 내가 작성한 다이어리 리스트
  @Transactional(readOnly = true)
  @Override
  public PageResultDTO<DiaryDTO, Diary> getMyDiaryPostList(PageRequestDTO requestDTO) {
    Pageable pageable = requestDTO.getPageable(Sort.by("dino").descending());
    BooleanBuilder booleanBuilder = getMyDiaryList(requestDTO);
    Page<Diary> result = repository.findAll(booleanBuilder, pageable);
    Function<Diary, DiaryDTO> fn = new Function<Diary, DiaryDTO>() {
      @Override
      public DiaryDTO apply(Diary t) {
        return diaryService.entityToDTO(t);
      }
    };
    return new PageResultDTO<>(result, fn);
  }

  // 내가 쓴 글만 확인 할 수 있어야한다.
  private BooleanBuilder getMyDiaryList(PageRequestDTO requestDTO) {
    String type = requestDTO.getType();
    String keyword = requestDTO.getKeyword();
    String writer = requestDTO.getWriter();
    List<String> tagList = requestDTO.getTagList();
    QDiary qDiary = QDiary.diary;

    log.info(writer);
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = qDiary.dino.gt(0L).and(qDiary.writer.eq(writer));
    booleanBuilder.and(expression);
    if (type == null || type.trim().length() == 0) {
      return booleanBuilder;
    }
    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if (type.contains("s")) { // s : stand for Search
      conditionBuilder
          .or(qDiary.title.contains(keyword))
          .or(qDiary.content.contains(keyword));
      tagList.forEach(new Consumer<String>() {
        @Override
        public void accept(String t) {
          Optional<Tag> temp = tagRepository.findByTagName(t);
          if (temp.isPresent()) {
            conditionBuilder.and(qDiary.tagList.contains(temp.get()));
          }
        }
      });
    }
    booleanBuilder.and(conditionBuilder);
    return booleanBuilder;
  }

}
