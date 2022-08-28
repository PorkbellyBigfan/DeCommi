package org.zerock.decommi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.zerock.decommi.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @EntityGraph(attributePaths = { "writer" }, type = EntityGraphType.LOAD)
    @Query("select d from Diary d where d.dino = :dino ")
    Optional<Diary> getDiaryWithWriter(Long dino);

    @Query("select d from Diary d ordered by d.dino desc")
    List<Diary> getDiaryList();

}
