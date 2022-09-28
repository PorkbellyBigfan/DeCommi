package org.zerock.decommi.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.member.Member;

@Repository
public interface AdminRepository extends JpaRepository<Diary, Long>, QuerydslPredicateExecutor<Diary> {

    @Query("select du from Diary du where writer =:mid")
    Optional<List<Diary>> getDiaryListByUserid(Long mid);
}
