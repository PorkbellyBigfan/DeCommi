package org.zerock.decommi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.zerock.decommi.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

        // // 페이징 처리 안된 다이어리 게시글 리스트
        @Query("select d, t, count(distinct r) from Diary d "
                        + " left outer join Tag t on t.diary = d "
                        + " left outer join Reply r on r.diary = r "
                        + " where d.dino=:dino group by t ")
        List<Object[]> getDiaryWithAll(Long dino);

        // 페이징 처리된 다이어리게시글리스트
        @Query("select d, t, count(distinct r) from Diary d "
                        + " left outer join Tag t on t.diary = d "
                        + " left outer join Reply r on r.diary = d "
                        + " group by d ")
        Page<Object[]> getListPage(Pageable pageable);

        @EntityGraph(attributePaths = { "writer" }, type = EntityGraphType.LOAD)
        @Query("select d from Diary d where d.dino = :dino ")
        Optional<Diary> getDiaryWithWriter(Long dino);

        // @Query("select d from Diary d ordered by d.dino desc")
        // List<Diary> getDiaryList();

}
