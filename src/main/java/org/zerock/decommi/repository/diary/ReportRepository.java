package org.zerock.decommi.repository.diary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.entity.diary.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByReid(Long reid);

    @Query("select r from Report r where mid_mid=:mid and dino_dino=:dino")
    Optional<Report> checkReportLogByMemberIdAndDiaryId(Long mid, Long dino);

    @Query(value = "SELECT COUNT(r.reid) FROM d_report as r WHERE r.dino_dino =:dino ", nativeQuery = true)
    Long getReportCntByDino(Long dino);

    @Modifying
    @Transactional
    @Query("delete from Report r where mid_mid=:mid")
    void deleteByMid(Long mid);

}
