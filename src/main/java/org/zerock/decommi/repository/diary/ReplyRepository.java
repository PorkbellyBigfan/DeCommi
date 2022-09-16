package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.member.Member;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
    // //게시글번호로 다이어리 리스트 조회
    // @Query("select r from Reply r where r.diary.dino=:dino")
    // List<Reply> findByDino(Long dino);
    
    // //댓글 작성자 찾기
    // Optional<Reply> findByEmail(Member email);

    // @Query("select r from reply r where r.rno=:rno and member_email=:email ")
    // Optional<Reply> getReplyByRnoAndEmail(Long rno, String email);


    // //댓글 리스트
    // @Query("select r from reply r where diary_dino=:dino order by reply_group asc, reply_order asc, reply_depth desc")
    // Optional<List<Reply>> getReplyListByDino(Long dino);

    // //댓글 페이지 리스트
    // @Query(value = "select r FROM reply r where diary_dino=:dino order by reply_group asc, reply_order asc, reply_depth desc ",
    // countQuery = "select count(r) from reply r where diary_dino=:dino order by reply_group asc, reply_order asc, reply_depth desc ")
    // Page<Reply> getReplyPageList(Pageable pageable, Long dino);
}
