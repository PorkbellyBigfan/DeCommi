package org.zerock.decommi.repository;

import java.util.Optional;

import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.decommi.entity.Help;

public interface HelpRepository extends JpaRepository<Help, Long>, QuerydslPredicateExecutor<Help> {

    Help findByhbno(Long hbno);

    Help getByhbno(Long hbno);

    // getList 타입별로 만들기,, where절에 타입 확인

    @Query("select hp from Help hp where hp.hbno=:hbno and mid=:mid")
    Optional<Help> getHelpByMid(Long mid, Long hbno);

    @Query("select hp from Help hp where help_type='NOTICE' ")
    Page<Help> getNoticeList(Pageable pageable, BooleanBuilder booleanBuilder);

    @Query("select hp from Help hp where help_type='FAQ' ")
    Page<Help> getFAQList(Pageable pageable, BooleanBuilder booleanBuilder);

    // @Query("select hp from Help hp left join b.writer")
    // Object getHelpByDino(@Param("dino") Long dino);
}
