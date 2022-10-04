// package org.zerock.decommi.service.member;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Optional;
// import java.util.function.Function;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;
// import org.zerock.decommi.dto.LikeTagListDTO;
// import org.zerock.decommi.entity.member.LikeTagList;
// import org.zerock.decommi.repository.diary.TagRepository;
// import org.zerock.decommi.repository.member.LikeTagListRepository;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Service
// @Log4j2
// @RequiredArgsConstructor
// public class LikeTagListServiceImpl implements LikeTagListService{
// private final LikeTagListRepository likeTagListRepository;
// private final LikeTagListService likeTagListService;

// @Override
// public List<LikeTagList> getLikeTagList(Long mid) {
// // TODO Auto-generated method stub
// return null;
// }
// // 선호태그리스트에 태그 추가 또는 삭제
// @Override
// public Boolean editLikeTagList(LikeTagListDTO dto) {
// Optional<LikeTagList> checking =
// likeTagListRepository.checkLikeTagListByMid(dto.getMid());
// LikeTagList entity = likeTagListDTOtoEntity(dto);
// if (checking.isPresent()) {
// return false; // 이미 리스트에 있으면 삭제
// } else {
// likeTagListRepository.save(entity);
// return true; // 리스트에 없는 태그면
// }
// }
// }
