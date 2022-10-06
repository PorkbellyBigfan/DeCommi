package org.zerock.decommi.service.member;

import java.util.List;
import java.util.Optional;

import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.entity.member.Member;

public interface LikeTagListService {

  // 회원 가입시 selectLikeTagList 페이지를 띄워주는데 스킵이 가능하다.
  // 차후 수정을 하려면 nav에 있는 회원정보수정 페이지에 접속해서 수정이 가능하다.
  // 1. 모든 태그 목록을 보여줘야한다. ordered by 많이 사용된 순이면 좋을 것이다.
  // 2. 그중에서 내가 체크한 선호태그 목록들을 보여줄 수 있어야한다. (front단에서 내가 체크한 태그면 색깔을 다르게 표현하면
  // 좋을것같다.)
  // 3. 선호태그를 추가 또는 삭제가 가능해야한다.

  // 개인 역량부족으로 위의 계획은 파기됨.
  // 1. 내가 선호하는 태그리스트를 보여줘야한다.
  // 2. 추가, 삭제 이벤트를 만든다.
  // 추가의 경우 문제가 생길 것 같다. 태그 테이블(DB)에 존재하지 않는 태그를 선호태그로 추가하려는 걸 막을 수 없다.

  Boolean editLikeTagList(LikeTagListDTO dto);
  Optional<List<String>>getLikeTagList(String email); //선호태그리스트가 null 값일 수도 있으니까 optional에다 집어 넣음

  // 선호태그리스트
  default LikeTagList dtoToEntity(LikeTagListDTO dto) {
    LikeTagList likeTagList = LikeTagList.builder()
        .lid(dto.getLid())
        .email(dto.getEmail())
        .tagName(dto.getTagName())
        .build();
    return likeTagList;
  }

  default LikeTagListDTO entityToDTO(LikeTagList likeTagList) {
    LikeTagListDTO dto = LikeTagListDTO.builder()
        .lid(likeTagList.getLid())
        .tagName(likeTagList.getTagName())
        .email(likeTagList.getEmail())
        .build();
    return dto;
  }
}
