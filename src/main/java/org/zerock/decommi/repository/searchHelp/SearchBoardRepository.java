package org.zerock.decommi.repository.searchHelp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.decommi.entity.Help;

public interface SearchBoardRepository {
  Help search1();
  Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
  
}
