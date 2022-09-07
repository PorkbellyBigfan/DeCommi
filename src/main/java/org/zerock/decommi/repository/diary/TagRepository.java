package org.zerock.decommi.repository.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.diary.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
