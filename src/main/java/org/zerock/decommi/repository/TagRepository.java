package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
