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
  public Optional<List<String>> getLikeTagList(Long mid) {
    Optional<List<String>> result = likeTagListRepository.getLikeTagList(mid);
    if(result.isPresent()){
      return result;
    }else{
      return null;
    }
  }


  // 선호태그리스트에 태그 추가 또는 삭제
  @Override
  public Boolean editLikeTagList(LikeTagListDTO dto) {
    Optional<List<LikeTagList>> checkLikeTagList = likeTagListRepository.checkLikeTagListByMidAndLid(dto.getMid());
    LikeTagList entity = dtoToEntity(dto);
    dto.setTagName(dto.getTagName());
    if(checkLikeTagList.isPresent()){
      likeTagListRepository.deleteAll(checkLikeTagList.get());
      return false;
    }else{
      if(tagRepository.checkTagName(dto.getTagName()).isPresent()){
        likeTagListRepository.save(entity);
        return true;
      }else{
        return false;
      }
    }
  }

}
