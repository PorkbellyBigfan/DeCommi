package org.zerock.decommi.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.LikeTagListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class LikeTagListServiceImpl implements LikeTagListService{
  private final LikeTagListRepository likeTagListRepository;
  private final LikeTagListService likeTagListService;
  // 선호태그리스트 목록 가져오기
  // @Override
  // public List<LikeTagListDTO> getLikeTagList(Long mid) {
  //   List<LikeTagList> result = likeTagListRepository.getLikeTagList(mid);
  //   return result.stream().map(new Function<LikeTagList, LikeTagListDTO>() {
  //     @Override
  //     public LikeTagListDTO apply(LikeTagList t) {
  //       return likeTagListEntitytoDTO(t);
  //     }
  //   }).collect(Collectors.toList());
  // }
  @Override
  public Optional<List<LikeTagList>> getLikeTagList(Long mid) {
    Optional<List<LikeTagList>> result = likeTagListRepository.getLikeTagList(mid);
    if(result.isPresent()){
      return result;
    }else{
      return null;
    }
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
}
