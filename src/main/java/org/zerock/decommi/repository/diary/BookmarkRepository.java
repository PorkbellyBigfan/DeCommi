package org.zerock.decommi.repository.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.decommi.entity.member.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
