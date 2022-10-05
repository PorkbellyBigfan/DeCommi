package org.zerock.decommi.service.member;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.LikeTagListDTO;
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
  private final MemberRepository memberRepository;

  // @Override
  // public Optional<List<LikeTagList>> getLikeTagList(Long mid) {
  //   // TODO Auto-generated method stub
  //   return null;
  // }
  @Override
  public Optional<List<LikeTagList>> getLikeTagList(Long mid) {
    Optional<List<LikeTagList>> result = likeTagListRepository.getLikeTagList(mid);
    return result;
  }
  // 선호태그리스트에 태그 추가 또는 삭제
  @Override
  public Boolean addLikeTagList(LikeTagListDTO dto) {
    LikeTagList result = dtoToEntity(dto);
    likeTagListRepository.save(result);
    log.info(result);
    Optional<List<LikeTagList>> checking = likeTagListRepository.checkLikeTagListByMid(dto.getMid());
    if(checking.isPresent()){
      log.info("checking is present result ::::"+result);
      log.info("checking"+checking);
    }
    return true;
  }
  
}
