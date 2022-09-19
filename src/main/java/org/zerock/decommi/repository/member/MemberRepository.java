package org.zerock.decommi.repository.member;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.zerock.decommi.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.id=:id")
  Optional<Member> findById(String id);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.id=:id and m.fromSocial=:social ")
  Optional<Member> findByIdWithSocial(String id, boolean social);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.email=:email and m.fromSocial=:social ")
  Optional<Member> findByEmail(String email, boolean social);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.email=:email")
  Optional<Member> findByEmail(String email);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query(value = "select m from Member m ", countQuery = "select count(m) from Member m")
  Page<Member> getPageList(Pageable pageable);
}
