package org.zerock.decommi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}