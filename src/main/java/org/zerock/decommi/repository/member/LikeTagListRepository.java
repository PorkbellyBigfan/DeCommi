package org.zerock.decommi.repository.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.zerock.decommi.entity.member.LikeTagList;

public interface LikeTagListRepository {
  
  @Query("select l from LikeTagList l where mid=:mid")
  Optional<List<LikeTagList>> getLikeTagList(Long mid);
}
