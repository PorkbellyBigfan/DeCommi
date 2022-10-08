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
    log.info("사용자의 email ::" + email);
    log.info("사용자가 선택한 tagName " + tagName);
    Optional<LikeTagList> checkLikeTag = likeTagListRepository.checkLikeTagListByEmailAndTagName(email, tagName);
    LikeTagListDTO dto = LikeTagListDTO.builder().email(email).tagName(tagName).build();
    LikeTagList entity = dtoToEntity(dto);
    log.info("checkLikeTag ::::: " + checkLikeTag);
    log.info("dto :::::" + dto);
    log.info("entity :::::" + entity);
    if (checkLikeTag.isPresent()) {
      // 존재할경우 likeTagList 테이블에서 해당 태그이름의 행을 삭제
      log.info("checkLikeTag ::::::: " + checkLikeTag.get());
      likeTagListRepository.delete(checkLikeTag.get());
      return false;
    } else {
      // 존재하지 않을경우 likeTagList에 해당 tagName 추가
      // 추가하기전에 해당 tagName이 tagRepository에 존재하는지 확인
      if (tagRepository.checkTagName(tagName).isPresent()) {
        // 만일 tagRepository에 해당 tagName이 존재한다고하면 likeTagList 테이블에 해당 태그 추가
        likeTagListRepository.save(entity);
        return true;
      } else {
        // 방법1. 만일 tagRepository에 해당 tagName이 존재하지 않는다고하면, 해당 태그를 생성후 likeTagList 테이블에
        // 저장하는 방법이있다.
        // 하지만 fk값떄문에 문제 생길 위험 있음
        Tag.builder().tagName(tagName).build();
        likeTagListRepository.save(entity);
        return true;
        // 방법2. 종료
      }
    }
  }

}
