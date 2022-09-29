package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  // 해당 게시글에 있는 태그 리스트를 가져오는 쿼리문
  @Query("select t from Tag t where diary_dino=:dino ")
  List<Tag> getList(Diary dino);

  // 게시글번호와 태그이름을 찾음
  Optional<Tag> findByDinoAndTagName(Diary dino, String tagName);

  List<Tag> findByDino(Diary dino);

  @Query("select t from Tag t where tag_name like %:keyword%")
  Optional<List<Tag>> getAidListByHashTagName(String keyword);

}
