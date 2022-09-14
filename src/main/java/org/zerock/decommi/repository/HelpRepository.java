package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.HelpBoard;

public interface HelpRepository extends JpaRepository<HelpBoard, Long> {

}
