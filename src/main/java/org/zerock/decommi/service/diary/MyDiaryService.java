package org.zerock.decommi.service.diary;

import java.util.List;

import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.entity.member.Member;

public interface MyDiaryService {
  PageResultDTO<DiaryDTO, Diary> getMyDiaryPostList(PageRequestDTO requestDTO);

  List<LikeTagListDTO> LikeTagList(Long mid);

  Boolean editLikeTagList(LikeTagListDTO dto);

  // Boolean deleteLikeTagList(LikeTagListDTO dto);

  // 선호태그리스트
  default LikeTagList likeTagListDTOtoEntity(LikeTagListDTO dto) {
    Member member = Member.builder().mid(dto.getMid()).build();
    Tag tagList = Tag.builder().tagId(dto.getTagId()).build();
    Tag tagName = Tag.builder().tagId(dto.getTagId()).tagName(dto.getLikeTagName()).build();
    LikeTagList likeTagList = LikeTagList.builder()
        .lid(dto.getLid())
        .mid(member.getMid())
        .tagId(tagList.getTagId())
        .tagName(tagName.getTagName())
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
