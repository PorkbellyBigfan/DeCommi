package org.zerock.decommi.repository.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.diary.DiaryTag;

public interface DiaryTagRepository extends JpaRepository<DiaryTag, Long> {

}
