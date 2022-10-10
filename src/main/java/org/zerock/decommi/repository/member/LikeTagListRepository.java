package org.zerock.decommi.repository.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.entity.member.Member;

@Repository
public interface LikeTagListRepository extends JpaRepository<LikeTagList, Long> {

  LikeTagList getByEmail(String email);

  // id로 다이어리 삭제하기
  @Modifying
  @Transactional
  @Query("delete from LikeTagList where tagName =:tagName")
  void deleteLikeTagByTagName(String tagName);

  @Query("select l from LikeTagList l where l.email=:email and l.tagName =:tagName ")
  Optional<LikeTagList> checkLikeTagListByEmailAndTagName(String email, String tagName);

  @Query("select tagName from LikeTagList l where email=:email ")
  List<String> getLikeTagList(String email);

  @Query("select l from LikeTagList l where l.email=:email ")
  Optional<List<LikeTagList>> getAllLikeTagList(String email);
}
