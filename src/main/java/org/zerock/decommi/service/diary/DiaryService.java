package org.zerock.decommi.service.diary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Member;

public interface DiaryService {
    // 등록
    Long register(DiaryDTO diaryDTO);

    // 수정
    void modify(DiaryDTO diaryDTO);

    // 삭제
    void delete(Long dino);

    // 페이징 처리 안된 리스트
    // List<DiaryDTO> getDiaryList();

    // 게시글 리스트 조회(Pagenated List)
    PageResultDTO<DiaryDTO, Object[]> getPageList(PageRequestDTO req);

    // 다이어리를 클릭했을때 자세히 보기
    DiaryDTO getDiary(Long dino);

    // 댓글 아이콘을 클릭했을때, 해당 다이리에 달린 댓글들 보여주기
    // DiaryDTO getReplyList(Long dino);

    default DiaryDTO entityToDTO(Diary diary, List<Tag> tag, Long replyCnt, Long heartCnt
    // , Long bookmarkCnt,Long reportCnt
    ) {
        DiaryDTO diaryDTO = DiaryDTO.builder()
                .title(diary.getTitle())
                .content(diary.getContent())
                .openYN(diary.isOpenYN())
                .commentYN(diary.isCommentYN())
                .writerEmail(diary.getWriter().getEmail())
                .build();
        List<TagDTO> tagDTOs = tag.stream().map(
                new Function<Tag, TagDTO>() {
                    @Override
                    public TagDTO apply(Tag t) {
                        return TagDTO.builder()
                                .tagId(t.getTagId())
                                .tagName(t.getTagName())
                                .isSubTag(t.isSubTag())
                                .tagGroup(t.getTagGroup())
                                .build();
                    };
                }).collect(Collectors.toList());
        diaryDTO.setTagDTOList(tagDTOs);
        diaryDTO.setReplyCnt(replyCnt.intValue());
        diaryDTO.setHeartCnt(heartCnt.intValue());
        // diaryDTO.setBookmarkCnt(bookmarkCnt.intValue());
        // diaryDTO.setReportCnt(reportCnt.intValue());
        return diaryDTO;
    }

    default Map<String, Object> dtoToEntity(DiaryDTO diaryDTO) {
        // DiaryDTO로 부터 Diary, Tag 두개를 나눠서 담기위한 map 선언
        Map<String, Object> entityMap = new HashMap<>();

        // 첫번째 Element of Map
        Diary diary = Diary.builder()
                .dino(diaryDTO.getDino())
                .title(diaryDTO.getTitle())
                .content(diaryDTO.getContent())
                .openYN(diaryDTO.isOpenYN())
                .commentYN(diaryDTO.isCommentYN())
                .writer(Member.builder().email(diaryDTO.getWriterEmail()).build())
                .build();
        entityMap.put("diary", diary);
        List<TagDTO> tagDTOList = diaryDTO.getTagDTOList();
        // Tag가 있을때만 TagDTO를 Tag로 변환
        if (tagDTOList != null && tagDTOList.size() > 0) {
            List<Tag> tagList = tagDTOList.stream().map(
                    new Function<TagDTO, Tag>() {
                        @Override
                        public Tag apply(TagDTO t) {
                            Tag tag = Tag.builder()
                                    .tagId(t.getTagId())
                                    .tagName(t.getTagName())
                                    .isSubTag(t.isSubTag())
                                    .tagGroup(t.getTagGroup())
                                    .build();
                            return tag;
                        }
                    }).collect(Collectors.toList());
            // Second Element of Map
            entityMap.put("tagList", tagList);
        }
        return entityMap;
    }

}
