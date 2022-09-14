package org.zerock.decommi.repository.diary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

  @Query("select t from Tag t where t.tagName =:tagName")
  List<Tag> findByTagName(String tagName);

  @Query("select t from Tag t where t.tagGroup =:tagGroup")
  List<Tag> findByTagGroup(Long tagGroup);

  @Query("select t from Tag t where t.diary =:diary")
  List<Tag> findByDiary(Diary diary);
}
