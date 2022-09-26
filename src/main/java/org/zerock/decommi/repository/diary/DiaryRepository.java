package org.zerock.decommi.repository.diary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.vo.DiaryPostList;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
  // , DiaryCustomRepository
  // 번호로 게시글 가져오기
  Diary getByDino(Long dino);

  // 번호로 게시글 조회
  Diary findByDino(Long dino);

  @Query("select d from Diary d where d.dino=:dino "
      + " ")
  Optional<Diary> getDiaryWithAll(Long dino);

  // 태그가 포함된 다이어리 리스트
  @EntityGraph(attributePaths = { "tags", "files", "replyList" }, type = EntityGraphType.LOAD)
  @Query(value = "select d from Diary d")
  Page<Diary> getDiaryListWithTagAndReply(Pageable pageable);

  // 관리자페이지에서 사용할것같음
  // 글작성자와 게시글 번호 가져오기
  @Query("select d from Diary d where writer=:id and dino=:dino")
  Optional<Diary> getDiaryByDinoAndId(Long dino, String id);

  // 댓글카운트, 하트카운트, 북마크카운트, 신고카운트 추가해야됨
  @Query("SELECT m.id as writer, d.dino as dino, d.title as title, d.content as content, d.regDate as regDate, "
      + "d.openYN as openYN, d.replyYN as replyYN, COUNT(r.rno) as replyCnt , COUNT(h.hid) as heartCnt, COUNT(rp.reid) as reportCnt, COUNT(b.bid) as bookmarkCnt "
      + "from Diary d "
      + "left join Member m on m.id = d.writer "
      + "left join Tag t on t.dino = d "
      + "left join Reply r on r.dino = d "
      + "left join Heart h on h.dino = d "
      + "left join Bookmark b on b.dino = d "
      + "left join Report rp on rp.dino = d "
      + "where d.openYN = 1L "
      + "group by d.dino ")
  Optional<List<getDiaryPostList>> getList(Sort sort);

  // 댓글카운트, 하트카운트, 북마크카운트, 신고카운트 추가해야됨
  // @Query("select m.id, d.dino, d.title, d.content, d.openYN, d.replyYN,
  // d.regDate, d.modDate "
  // + "from Diary d "
  // + "left join Member m on m.id = d.writer "
  // + "where d.title LIKE CONCAT('%',:search,'%') Or "
  // + "d.content LIKE CONCAT('%',:search,'%') Or "
  // + "ORDER BY d.dino DESC ")
  // List<Object[]> getListByTitleOrContent(String search);

  // Dino로 파일 삭제하기
  @Modifying
  @Query("delete from File f where f.dino.dino=:dino")
  void deleteFileByDino(Long dino);

  public interface getDiaryPostList {
    Long getDino();

    String getTitle();

    String getWriter();

    String getContent();

    Long getReplyCnt();

    Long getHeartCnt();

    Long getBookmarkCnt();

    Long getReportCnt();

    LocalDateTime getRegDate();
  }

  public interface getDiaryPost {
    Long getDino();

    String getTitle();

    String getWriter();

    String getContent();

    Long getReplyCnt();

    Long getHeartCnt();

    Long getBookmarkCnt();

    Long getReportCnt();

    LocalDateTime getRegDate();

    LocalDateTime getModDate();
  }
}
