package org.zerock.decommi.admin.service;

import org.zerock.decommi.admin.dto.PageRequestDTO;
import org.zerock.decommi.admin.dto.PageResultDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.ReportDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Report;
import org.zerock.decommi.entity.member.Member;

public interface AdminService {

    // 회원 관리
    PageResultDTO<MemberDTO, Member> getUserList(PageRequestDTO requestDTO);

    Boolean UserModifier(MemberDTO dto);

    Boolean UserDeleter(MemberDTO dto);

    // 태그 관리
    // List<String> getTag(Long tagId);

    Boolean tagModify(TagDTO dto);

    Boolean tagDelete(TagDTO dto);

    // 다이어리 관리
    PageResultDTO<DiaryDTO, Diary> getDiaryListWithTagAndReply(PageRequestDTO requestDTO);

    Boolean diaryDelete(DiaryDTO dto);

    // 신고 관리
    PageResultDTO<ReportDTO, Report> getReportList(PageRequestDTO requestDTO);

    Boolean reportDelete(ReportDTO dto);

    // 어드민 확인
    Boolean adminChecker(Long mid);

}
