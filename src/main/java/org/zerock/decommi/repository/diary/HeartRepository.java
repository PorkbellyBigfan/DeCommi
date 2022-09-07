package org.zerock.decommi.repository.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.diary.Heart;

public interface HeartRepository extends JpaRepository<Heart, Long> {

}
