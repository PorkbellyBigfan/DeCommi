package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Diary;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

  //번호로 게시글 가져오기
  Diary getByDino(Long dino);
  //번호로 게시글 조회
  Diary findByDino(Long dino);

  //태그가 포함된 다이어리 리스트
  @EntityGraph(attributePaths = {"tags"}, type = EntityGraphType.LOAD)
  @Query(value = "select d from Diary d")
  Page<Diary> getDiaryListWithTag(Pageable pageable);

  //페이징 처리 안된 다이어리 리스트
  @Query("select d from Diary d")
  List<Diary>getList();

  // @Query("select d FROM Diary d WHERE title =:title or d.desc like :title%")
  // List<Diary>findByTitle(String title);

  // @Query(" select d, t.tagName FROM Diary d "+
  //        " left join Tag t "+
  //        " WHERE t.tagName like :tagsearch" +
  //        "ORDER BY d.dino DESC"
  //       )
  // List<Object[]>getDiaryListByTagName(String tagsearch);



}
