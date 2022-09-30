package org.zerock.decommi.repository.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.member.LikeTagList;

@Repository
public interface LikeTagListRepository extends JpaRepository<LikeTagList, Long> {

  // @Query("select l from LikeTagList l where mid=:mid ")
  // Optional<LikeTagList> checkLikeTagListByMid(Long mid);

  @Query("select m.mid as mid, l from LikeTagList l " +
      "left join Member m on m.mid = l " +
      "group by l.mid ")
  List<LikeTagList> getLikeTagList(Long mid);
}
