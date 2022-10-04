package org.zerock.decommi.service.diary;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
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
import org.zerock.decommi.entity.diary.QDiary;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.LikeTagListRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MyDiaryServiceImpl implements MyDiaryService {
  private final DiaryRepository repository;
  private final LikeTagListRepository likeTagListRepository;
  private final DiaryService diaryService;

  // 내가 작성한 다이어리 리스트
  @Transactional(readOnly = true)
  @Override
  public PageResultDTO<DiaryDTO, Diary> getMyDiaryPostList(PageRequestDTO requestDTO) {
    Pageable pageable = requestDTO.getPageable(Sort.by("dino").descending());
    BooleanBuilder booleanBuilder = searchMyDiary(requestDTO);
    Page<Diary> result = repository.findAll(booleanBuilder, pageable);
    Function<Diary, DiaryDTO> fn = new Function<Diary, DiaryDTO>() {
      @Override
      public DiaryDTO apply(Diary t) {
        return diaryService.entityToDTO(t);
      }
    };
    log.info(" service ::: result ::: " + result);
    log.info(" service ::: requestDTO ::: " + requestDTO);
    return new PageResultDTO<>(result, fn);
  }

  // 내가 쓴 글만 확인 할 수 있어야한다.
  private BooleanBuilder searchMyDiary(PageRequestDTO requestDTO) {
    String type = requestDTO.getType();
    String keyword = requestDTO.getKeyword();
    String id = requestDTO.getId();
    List<String> tagList = requestDTO.getTagList();

    QDiary qDiary = QDiary.diary;
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = qDiary.dino.gt(0L).and(qDiary.writer.eq(id));
    booleanBuilder.and(expression);
    if (type == null || type.trim().length() == 0) {
      return booleanBuilder;
    }

    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if (type.contains("d")) { // "d" stand for Diary
      conditionBuilder
          .or(qDiary.title.contains(keyword))
          .or(qDiary.content.contains(keyword));
    }
    if (type.contains("t")) { // "t" stand for Tag
      // conditionBuilder
      // .or(qDiary.tagList.contains(tagList.stream().map(new
      // Function<String,String>() {
      // @Override
      // public String apply(String dto) {
      // return tagDTOtoEntity(dto);
      // }
      // }).collect(Collectors.toList())));
    }
    booleanBuilder.and(conditionBuilder);
    return booleanBuilder;

  }

  // 선호태그리스트

  // 선호태그리스트 목록 가져오기
  @Override
  public List<LikeTagListDTO> LikeTagList(Long mid) {
    List<LikeTagList> result = likeTagListRepository.getLikeTagList(mid);
    return result.stream().map(new Function<LikeTagList, LikeTagListDTO>() {
      @Override
      public LikeTagListDTO apply(LikeTagList t) {
        return likeTagListEntitytoDTO(t);
      }
    }).collect(Collectors.toList());
  }

  // 선호태그리스트에 태그 추가 또는 삭제
  @Override
  public Boolean editLikeTagList(LikeTagListDTO dto) {
    Optional<LikeTagList> checking = likeTagListRepository.checkLikeTagListByMid(dto.getMid());
    LikeTagList entity = likeTagListDTOtoEntity(dto);
    if (checking.isPresent()) {
      return false; // 이미 리스트에 있으면 삭제
    } else {
      likeTagListRepository.save(entity);
      return true; // 리스트에 없는 태그면
    }

  }

  // 선호태그리스트에 태그 삭제
  // @Override
  // public void deleteLikeTagList(LikeTagListDTO dto) {
  // LikeTagList entity = likeTagListDTOtoEntity(dto);
  // likeTagListRepository.delete(entity);
  // }

}
