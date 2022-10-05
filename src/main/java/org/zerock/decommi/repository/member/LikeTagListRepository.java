package org.zerock.decommi.repository.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.dto.LikeTagListDTO;
import org.zerock.decommi.entity.member.LikeTagList;
import org.zerock.decommi.entity.member.Member;

@Repository
public interface LikeTagListRepository extends JpaRepository<LikeTagList, Long> {

  LikeTagList getByMid(Long mid);

  @Query("select l from LikeTagList l where mid=:mid ")
  Optional<List<LikeTagList>>checkLikeTagListByMid(Long mid);

  // @Query("select ltl from LikeTagList ltl where mid =:mid ")
  // List<LikeTagList> getLikeTagList(Long mid);

  // @Query("select ltl.mid as mid, distinct(ltl.tagName) from likeTagList ltl
  // where mid=:mid group by mid")
  // List<LikeTagListDTO> getAllLikeTagListByMid(Member mid);
}
