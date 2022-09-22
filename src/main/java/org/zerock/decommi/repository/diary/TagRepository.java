package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

  @Query("select t from Tag t where dino =:dino ")
  List<Tag> findByDino(Long dino);

  @Query("select t from Tag t where t.tagName =:tagName")
  List<Tag> findByTagName(String tagName);

  @Query("select t from Tag t where t.tagGroup =:tagGroup")
  List<Tag> findByTagGroup(Long tagGroup);

  @Query("select t from Tag t where t.dino =:dino")
  List<Tag> findByDiary(Diary dino);

  @Query("select t from Tag t where diary_dino=:dino ")
  List<TagDTO> getList(Long dino);

  @Query("select t from Tag t where tag_name like %:keyword%")
  Optional<List<Tag>> getAidListByHashTagName(String keyword);

}
