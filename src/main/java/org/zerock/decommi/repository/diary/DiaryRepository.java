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
import org.zerock.decommi.entity.member.Member;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

  // 번호로 게시글 가져오기
  Diary getByDino(Long dino);

  // 번호로 게시글 조회
  Diary findByDino(Long dino);

  // 태그가 포함된 다이어리 리스트
  @EntityGraph(attributePaths = { "tags", "replies" }, type = EntityGraphType.LOAD)
  @Query(value = "select dlwt from Diary dlwt") // dlwt = DiaryList With Tag
  Page<Diary> getDiaryListWithTag(Pageable pageable);

  // 글작성자와 게시글 번호 가져오기
  @Query("select d from Diary d where writer=:id and dino=:dino")
  Optional<Diary> getDiaryByDinoAndId(Long dino, String id);

  // @Query("select m.id d.dino, d.title, d.content, d.openYN, d.replyYN d.regDate
  // "
  // + "from Diary d left join Member m "
  // + "on m.id=d.writer "
  // + "ORDER BY d.dino DESC ")
  // List<Object[]> getListAndAuthor();

  // @Query("select m.name d.dino, d.title, d.content, d.openYN, d.replyYN
  // d.regDate "
  // + "from Diary d left join Member m "
  // + "on m.mid=d.writer "
  // + "where m.name LIKE CONCAT('%',:search,'%') Or "
  // + "d.title LIKE CONCAT('%',:search,'%') Or "
  // + "ORDER BY d.dino DESC ")
  // List<Object[]> getListAndAuthorByAuthorOrDtitle(String search);

}
