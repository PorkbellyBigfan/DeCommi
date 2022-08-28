package org.zerock.decommi.service;

import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;

public interface DiaryService {
    // 게시글 등록, DiaryDTO의 primary key가 Long 타입
    Long register(DiaryDTO diaryDTO);

    PageResultDTO<DiaryDTO, Object[]> getList(PageRequestDTO req);

    // 다이어리를 클릭했을때 자세히 보기
    DiaryDTO getDiary(Long dino);

    // 댓글 아이콘을 클릭했을때, 해당 다이어리에 달린 댓글들 보여주기
    // DiaryDTO getReplyList(Long dino);

}
