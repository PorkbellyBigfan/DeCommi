// package org.zerock.decommi.service.member;

// import java.util.List;

// import org.zerock.decommi.dto.LikeTagListDTO;
// import org.zerock.decommi.entity.member.LikeTagList;
// import org.zerock.decommi.entity.member.Member;

// public interface LikeTagListService {
// List<LikeTagList> getLikeTagList(Long mid);
// Boolean editLikeTagList(LikeTagListDTO dto);

// //회원 가입시 selectLikeTagList 페이지를 띄워주는데 스킵이 가능하다.
// //차후 수정을 하려면 nav에 있는 회원정보수정 페이지에 접속해서 수정이 가능하다.
// //1. 모든 태그 목록을 보여줘야한다. ordered by 많이 사용된 순이면 좋을 것이다.
// //2. 그중에서 내가 체크한 선호태그 목록들을 보여줄 수 있어야한다. (front단에서 내가 체크한 태그면 색깔을 다르게 표현하면 좋을것
// 같다.)
// //3. 선호태그를 추가 또는 삭제가 가능해야한다.

// //개인 역량부족으로 위의 계획은 파기됨.
// //1. 내가 선호하는 태그리스트를 보여줘야한다.
// //2. 추가, 삭제 이벤트를 만든다.
// //추가의 경우 문제가 생길 것 같다. 태그 테이블(DB)에 존재하지 않는 태그를 선호태그로 추가하려는 걸 막을 수 없다.

// // 선호태그리스트
// default LikeTagList likeTagListDTOtoEntity(LikeTagListDTO dto) {
// Member member = Member.builder().mid(dto.getMid()).build();
// LikeTagList likeTagList = LikeTagList.builder()
// .lid(dto.getLid())
// .mid(member.getMid())
// .tagId(dto.getTagId())
// .tagName(dto.getLikeTagName())
// .build();
// return likeTagList;
// }

// default LikeTagListDTO likeTagListEntitytoDTO(LikeTagList likeTagList) {
// LikeTagListDTO dto = LikeTagListDTO.builder()
// .lid(likeTagList.getLid())
// .likeTagName(likeTagList.getTagName())
// .mid(likeTagList.getMid())
// .tagId(likeTagList.getTagId())
// .build();
// return dto;
// }
// }
