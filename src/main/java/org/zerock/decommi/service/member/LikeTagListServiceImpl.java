package org.zerock.decommi.service.member;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.repository.member.LikeTagListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class LikeTagListServiceImpl implements LikeTagListService {
  private final LikeTagListRepository likeTagListRepository;

  @Override
  public List<String> getLikeTagList(String email) {
    // log.info("service class email :::" + email);
    // log.info(email.substring(10, email.length() - 2));
    // if (email.contains("{")) {
    // email = email.substring(10, email.length() - 2);
    // }
    List<String> result = likeTagListRepository.getLikeTagList(email);
    // log.info("result.get() ::: " + result.get());
    if (result == null) {
      return null;
    } else {
      log.info("{ 가 없는 email : : : " + email);
      return result;
    }
  }

  // 선호태그리스트에 태그 추가 또는 삭제
  @Override
  public Boolean editLikeTagList(String tagName, String email) {
    log.info("email :: " + email);
    log.info("tagName :: " + tagName);

    Optional<LikeTagList> checkLikeTag = likeTagListRepository.checkLikeTagListByEmailAndTagName(email, tagName);
    if (checkLikeTag.isPresent()) {
      log.info("checkLikeTag :: " + checkLikeTag.get());
      // 존재할경우 likeTagList 테이블에서 해당 태그이름의 행을 삭제
      log.info("Before delete List :: " + likeTagListRepository.getLikeTagList(email));
      // on = false; // 이게 의미가 있는지 모르겠습니다.
      likeTagListRepository.delete(checkLikeTag.get());
      log.info("After delete List :: " + likeTagListRepository.getLikeTagList(email));
      return false;
    } else {
      // on = true; // 이게 의미가 있는지 모르겠습니다.
      likeTagListRepository.save(LikeTagList.builder().email(email).tagName(tagName).build());
      log.info(getLikeTagList(email));
      return true;
    }
  }

}
