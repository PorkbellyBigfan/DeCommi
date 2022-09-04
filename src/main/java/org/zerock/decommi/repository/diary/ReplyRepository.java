package org.zerock.decommi.repository.diary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // @Modifying
    // @Query("delete from Reply r where r.Diary.dino=:dino ") // 쿼리어노테이션
    // void deleteBydino(Long dino);

    // List<Reply> getRepliesByDiaryOrderByRno(Diary diary); // 쿼리메서드
}
