package org.zerock.decommi.service.diary;

import java.util.HashMap;

import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.diary.Diary;

public interface MyDiaryService {
  PageResultDTO<DiaryDTO, Diary> getMyDiaryPostList(PageRequestDTO requestDTO);
  
  // 구현실패
  // HashMap<String, Object> getSearchMyDiaryPostList(PageRequestDTO requestDTO);
}
