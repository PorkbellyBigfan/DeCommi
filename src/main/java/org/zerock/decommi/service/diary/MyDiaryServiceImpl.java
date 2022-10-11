package org.zerock.decommi.service.diary;

import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.QDiary;
import org.zerock.decommi.repository.diary.DiaryRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyDiaryServiceImpl implements MyDiaryService {
  private final DiaryRepository repository;
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
    String writer = requestDTO.getWriter();
    String keyword = requestDTO.getKeyword();
    QDiary qDiary = QDiary.diary;
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = qDiary.dino.gt(0L).and(qDiary.writer.eq(writer));
    booleanBuilder.and(expression);
    if (keyword == null) {
      return booleanBuilder;
    }
    BooleanBuilder conditionBuilder = new BooleanBuilder();
    conditionBuilder.or(qDiary.title.contains(keyword)).or(qDiary.content.contains(keyword));
    booleanBuilder.and(conditionBuilder);
    return booleanBuilder;
  }

}
