package org.zerock.decommi.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.zerock.decommi.entity.diary.Report;

@Repository
public interface AdminReportRepository extends JpaRepository<Report, Long>, QuerydslPredicateExecutor<Report> {

}
