package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

}
