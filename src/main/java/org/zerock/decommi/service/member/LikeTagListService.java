package org.zerock.decommi.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.entity.member.Member;

public interface LikeTagListService {
  Optional<List<LikeTagList>> getLikeTagList(Long mid);
  Boolean editLikeTagList(LikeTagListDTO dto);

  // Boolean deleteLikeTagList(LikeTagListDTO dto);

  // 선호태그리스트
  default LikeTagList likeTagListDTOtoEntity(LikeTagListDTO dto) {
    Member member = Member.builder().mid(dto.getMid()).build();
    LikeTagList likeTagList = LikeTagList.builder()
        .lid(dto.getLid())
        .mid(member.getMid())
        .tagId(dto.getTagId())
        .tagName(dto.getLikeTagName())
        .build();
    return likeTagList;
  }

  default LikeTagListDTO likeTagListEntitytoDTO(LikeTagList likeTagList) {
    LikeTagListDTO dto = LikeTagListDTO.builder()
        .lid(likeTagList.getLid())
        .likeTagName(likeTagList.getTagName())
        .mid(likeTagList.getMid())
        .tagId(likeTagList.getTagId())
        .build();
    return dto;
  }
}
