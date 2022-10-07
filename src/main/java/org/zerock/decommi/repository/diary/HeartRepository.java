package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Heart;
import org.zerock.decommi.entity.member.Member;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    @Query("select h from Heart h where mid=:mid and dino=:dino")
    Optional<Heart> checkHeartLogByMemberIdAndDiaryId(Long mid, Long dino);

    @Query("select h from Heart h where mid=:mid")
    Optional<List<Heart>> getList(Long mid);

    @Query(value = "SELECT COUNT(h.hid) FROM d_heart as h WHERE h.dino_dino =:dino ", nativeQuery = true)
    Long getHeartCntByDino(Long dino);

}
