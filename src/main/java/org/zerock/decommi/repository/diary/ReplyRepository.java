package org.zerock.decommi.repository.diary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.decommi.entity.diary.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.diary.dino=:dino")
    List<Reply> findByDino(Long dino);
}
