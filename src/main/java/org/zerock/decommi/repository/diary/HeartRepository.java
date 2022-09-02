package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Long> {

}
