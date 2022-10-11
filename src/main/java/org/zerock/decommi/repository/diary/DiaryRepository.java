package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.File;
import org.zerock.decommi.entity.diary.Reply;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long>, QuerydslPredicateExecutor<Diary> {
  // , DiaryCustomRepository
  // 번호로 게시글 가져오기
  Diary getByDino(Long dino);

  // 번호로 게시글 조회
  Diary findByDino(Long dino);

  List<Diary> findByWriter(String writer);

  // 글작성자와 게시글 번호 가져오기
  @Query("select d from Diary d where writer=:id and dino=:dino")
  Optional<Diary> getDiaryByDinoAndId(Long dino, String id);

  // @Query("SELECT COUNT(r.rno) as replyCnt, COUNT(h.hid) as heartCnt,
  // COUNT(b.bid) as bookmarkCnt, COUNT(rp.reid)as reportCnt from Diary d "
  // +
  // "left join Reply r on r.dino = d " +
  // "left join Heart h on h.dino = d " +
  // "left join Report rp on rp.dino = d " +
  // "left join Bookmark b on b.dino = d ")
  // Optional<Diary> getCounterByDino(Long dino);

  // 댓글카운트, 하트카운트, 북마크카운트, 신고카운트 추가해야됨
  // @Query("SELECT m.id as writer, d.dino as dino, d.title as title, d.content as
  // content, d.regDate as regDate, "
  // + "d.openYN as openYN, d.replyYN as replyYN, COUNT(r.rno) as replyCnt ,
  // COUNT(h.hid) as heartCnt, COUNT(rp.reid) as reportCnt, COUNT(b.bid) as
  // bookmarkCnt "
  // + "from Diary d "
  // + "left join Member m on m.id = d.writer "
  // + "left join Tag t on t.dino = d "
  // + "left join Reply r on r.dino = d "
  // + "left join Heart h on h.dino = d "
  // + "left join Bookmark b on b.dino = d "
  // + "left join Report rp on rp.dino = d "
  // + "where d.openYN = 1L "
  // + "group by d.dino ")
  // Optional<List<getDiaryPostList>> getList(Sort sort);

  // id로 다이어리 삭제하기
  @Modifying
  @Transactional
  @Query("delete from Diary d where writer=:writer")
  void deleteDiaryByWriter(String writer);

  // Dino로 파일 삭제하기
  @Modifying
  @Query("delete from File f where f.dino.dino=:dino")
  void deleteFileByDino(Long dino);

  public interface getDiaryPostList {
    Long getDino();

    String getWriter();

    String getTitle();

    String getContent();

    boolean isOpenYN();

    boolean isReplyYN();

    List<String> getTagList();

    List<Reply> getReplyList();

    List<File> getFiles();

    // Long getReplyCnt();
    // Long getHeartCnt();
    // Long getBookmarkCnt();
    // Long getReportCnt();
    // LocalDateTime getRegDate();
  }

  public interface getDiaryPost {
    Long getDino();

    String getWriter();

    String getTitle();

    String getContent();

    boolean isOpenYN();

    boolean isReplyYN();

    List<String> getTagList();

    List<Reply> getReplyList();

    List<File> getFiles();
  }
}
