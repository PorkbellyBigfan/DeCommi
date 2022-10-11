package org.zerock.decommi.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.member.Member;

@Repository
public interface AdminMemRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
    // 어드민인지 확인
    @Query("SELECT au.auth FROM Member au WHERE au.mid =:mid")
    Boolean Admincheck(Long mid);
}
