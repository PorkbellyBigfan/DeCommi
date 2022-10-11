package org.zerock.decommi.repository.api;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.decommi.entity.member.Member;

public interface ApiRepository extends JpaRepository<Member, String> {
  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.email=:email")
  Optional<Member> findByEmail(String email);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query("select m from Member m where m.email=:email and m.fromSocial=:social ")
  Optional<Member> findByEmail(String email, boolean social);

  @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
  @Query(value = "select c from Member c ", countQuery = "select count(c) from Member c")
  Page<Member> getPageList(Pageable pageable);
}
