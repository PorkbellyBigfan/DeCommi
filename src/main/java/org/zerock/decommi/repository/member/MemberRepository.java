package org.zerock.decommi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.zerock.decommi.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
    @Query("select m from Member m where m.email=:email")
    Optional<Member> findByEmail(String email);

}
