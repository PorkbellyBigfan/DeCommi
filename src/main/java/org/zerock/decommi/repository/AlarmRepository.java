package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.member.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

}
