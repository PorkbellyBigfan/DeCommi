package org.zerock.decommi.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.LikeTagListRepository;
import org.zerock.decommi.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class LikeTagListServiceImpl implements LikeTagListService {
  private final LikeTagListRepository likeTagListRepository;
  private final TagRepository tagRepository;

  @Override
  public Optional<List<String>> getLikeTagList(String email) {
    Optional<List<String>> result = likeTagListRepository.getLikeTagList(email);
    if (result.isPresent()) {
      return result;
    } else {
      return null;
    }
  }

  // 선호태그리스트에 태그 추가 또는 삭제
  @Override
  public Boolean editLikeTagList(String tagName, String email) {
    // log.info("사용자의 email ::" + email);
    // log.info("사용자가 선택한 tagName " + tagName);
    // LikeTagListDTO dto =
    // LikeTagListDTO.builder().email(email).tagName(tagName).build();
    // LikeTagList entity = dtoToEntity(dto);
    // likeTagListRepository.save(entity);// 여기서 lid 생성됨
    // log.info("dto :::::" + dto);
    // log.info("entity :::::" + entity);

    Optional<LikeTagList> checkLikeTag = likeTagListRepository.checkLikeTagListByEmailAndTagName(email, tagName);
    log.info("checkLikeTag ::::: " + checkLikeTag);
    if (checkLikeTag.isPresent()) {
      // // 존재할경우 likeTagList 테이블에서 해당 태그이름의 행을 삭제
      likeTagListRepository.delete(checkLikeTag.get());
      likeTagListRepository.flush();
      return false;
    } else {
      likeTagListRepository.save(LikeTagList.builder().email(email).tagName(tagName).build());
      log.info(getLikeTagList(email));
      return true;
    }
  }

}
