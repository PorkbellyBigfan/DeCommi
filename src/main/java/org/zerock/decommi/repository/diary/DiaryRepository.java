package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.query.Param;
import org.zerock.decommi.entity.diary.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

        // // 페이징 처리 안된 다이어리 게시글 리스트
        @Query("select d, t, count(distinct r),count(distinct h),count(distinct m) from Diary d "
                        + " left outer join Tag t on t.diary = d "
                        + " left outer join Reply r on r.diary = r "
                        + " left outer join Heart h on h.diary = d "
                        + " left outer join Bookmark m on m.diary = d "
                        + " where d.dino=:dino group by t ")
        List<Object[]> getDiaryWithAll(Long dino);

        // 페이징 처리된 다이어리게시글리스트
        @Query("select d, t, count(distinct r),count(distinct h),count(distinct m) from Diary d "
                        + " left outer join Tag t on t.diary = d "
                        + " left outer join Reply r on r.diary = d "
                        + " left outer join Heart h on h.diary = d "
                        + " left outer join Bookmark m on m.diary = d "
                        + " group by d ")
        Page<Object[]> getListPage(Pageable pageable);

        @EntityGraph(attributePaths = { "writer" }, type = EntityGraphType.LOAD)
        @Query("select d from Diary d where d.dino = :dino ")
        Optional<Diary> getDiaryWithWriter(Long dino);

        // @Query("select d from Diary d ordered by d.dino desc")
        // List<Diary> getDiaryList();

        // Diary, Writer
        @Query("select d, w from Diary d left join d.writer w where d.dino=:dino")
        Object getBoardWithWriter(@Param("dino") Long dino);

        // Diary, Reply
        @Query("select d, r from Diary d left join Reply r on r.diary=d where d.dino=:dino")
        List<Object[]> getBoardWithReply(@Param("dino") Long dino);

        // Diary, Writer, 댓글 갯수
        @Query(value = "select d,w,count(r) from Diary d left join d.writer w "
                        + "left join Reply r on r.diary=d group by b ", countQuery = "select count(b) from Diary d")
        Page<Object[]> getBoardWithReplyCount(Pageable pageable);

        // 글번호에 의해 Diary, Writer, 댓글 갯수
        @Query(value = "select d,w,count(r) from Diary d left join d.writer w "
                        + "left join Reply r on r.diary=d where d.dino=:dino ")
        Object getBoardByBno(@Param("dino") Long dino);

}
