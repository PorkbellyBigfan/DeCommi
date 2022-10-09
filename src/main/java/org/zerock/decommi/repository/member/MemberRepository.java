package org.zerock.decommi.repository.member;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.mid=:mid")
  Optional<Member> findByMid(Long mid);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.id=:id and m.fromSocial=:social ")
  Optional<Member> findByIdWithSocial(String id, boolean social);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.email=:email and m.fromSocial=:social ")
  Optional<Member> findByEmailWithSocid(String email, boolean social);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.email=:email")
  Optional<Member> findByEmail(String email);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.id=:id")
  Optional<Member> findByUserId(String id);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.id=:id")
  Member findByUserId2(String id);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query(value = "select m from Member m ", countQuery = "select count(m) from Member m")
  Page<Member> getPageList(Pageable pageable);

  // 비밀번호찾기
  @Query("select mid from Member where email=:email and (q1=:q1 or q2=:q2 or q3=:q3)")
  Long findMidByEmailAndQ(String email, String q1, String q2, String q3);

  // 비밀번호변경
  @Transactional
  @Modifying(clearAutomatically = true)
  @Query("update Member m set m.pw=:pw where m.mid=:mid")
  int changePwByMid(Long mid, String pw);

}
