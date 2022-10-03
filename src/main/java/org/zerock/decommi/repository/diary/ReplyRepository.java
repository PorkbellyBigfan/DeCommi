package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.member.Member;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 게시글번호로 다이어리 댓글 리스트 조회
    @Query("select r from Reply r where dino_dino =:dino order by reply_order asc, reply_group asc, reply_depth desc ")
    List<Reply> getReplyListByDino(Long dino);

    // //댓글 작성자 찾기
    Optional<Reply> findByMember(Member member);

    // 해당 게시글의 해당 작성자가 작성한 댓글 가져오기
    @Query("select r from Reply r where dino_dino=:dino and member_mid=:mid and reply_depth=0 ")
    Optional<Reply> getReplyByDinoAndMid(Diary dino, Member mid);

    @Query("select r from Reply r where r.rno=:rno and member_mid=:mid")
    Optional<Reply> getReplyByRnoAndMid(Long rno, Long mid);

    // 그룹별정렬??
    @Query("select replyGroup from Reply where dino_dino=:dino ORDER BY reply_group desc ")
    Optional<List<Long>> getLastestReplyGroupWhereMatchWithDino(Long dino);

    // 페이징 처리된 댓글리스트
    @Query(value = "select r from Reply r where dino_dino=:dino order by reply_group asc, reply_order asc, reply_depth desc ", countQuery = "select count(r) from Reply r where dino_dino=:dino order by reply_group asc, reply_order asc, reply_depth desc ")
    Page<Reply> getPageList(Pageable pageable, Long dino);

    // 댓글 리스트
    // @Query("select r from Reply r where diary_dino=:dino order by reply_group
    // asc, reply_order asc, reply_depth desc")
    // Optional<List<Reply>> getReplyListByDino2(Long dino);

    // 댓글 페이지 리스트
    // @Query(value = "select r from Reply r where diary_dino=:dino order by
    // reply_group asc, reply_order asc, reply_depth desc ", countQuery = "select
    // count(r) from Reply r where diary_dino=:dino order by reply_group asc,
    // reply_order asc, reply_depth desc ")
    // Page<Reply> getReplyPageList(Pageable pageable, Long dino);
}
