package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, Long>{
  
}
