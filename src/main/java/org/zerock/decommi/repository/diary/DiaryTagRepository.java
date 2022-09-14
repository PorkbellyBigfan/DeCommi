package org.zerock.decommi.repository.diary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.decommi.entity.diary.DiaryTag;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {

  @Query("select dt from DiaryTag dt where dt.diary.dino =:dino")
  List<DiaryTag> findByDino(Long dino);
}
