package org.zerock.decommi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.decommi.entity.Help;

public interface HelpRepository extends JpaRepository<Help, Long> {

    Help findByhbno (Long hbno);

    Help getByhbno (Long hbno);

    //getList 타입별로 만들기 ,, 타입뽑기


    // @Query("select hp from Help hp left join b.writer")
    // Object getHelpByDino(@Param("dino") Long dino);
}
