package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.entity.member.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("select b from Bookmark b where mid=:mid and dino=:dino")
    Optional<Bookmark> checkBookmarkLogByMemberIdAndDiary(Long mid, Long dino);

    @Query("select b from Bookmark b where mid=:mid")
    Optional<List<Bookmark>> getList(Long mid);

    @Query(value = "SELECT COUNT(b.bid) FROM d_bookmark as b WHERE b.dino_dino =:dino ", nativeQuery = true)
    Long getBookmarkCntByDino(Long dino);

    @Modifying
    @Transactional
    @Query("delete from Bookmark b where mid=:mid")
    void deleteByMid(Long mid);

}
