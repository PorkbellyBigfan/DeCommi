package org.zerock.decommi.service.diary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.ReplyRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository repository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final ReplyRepository replyRepository;

    @Override
    public String registerDiary(DiaryDTO dto, List<TagDTO> tagList) {
        Diary result = dtoToEntity(dto);
        repository.save(result);
        // 이미지 업로드 구현되면 추가예정
        // List<ImageList> lists = dto.getImage();
        for (TagDTO i : tagList) {
            Tag tagResult = tagDTOtoEntity(i);
            tagResult.updateDiary(result);
            tagRepository.save(tagResult);
        }
        return result.getDino().toString();
    }

    @Override
    public DiaryDTO checkBeforeDiaryModify(Long dino, String id) {
        // Optional<Diary> isit = repository.getDiaryByDinoAndId(dino, id);
        if (!isit.isPresent()) {
            return null;
        } else {
            DiaryDTO dto = entityToDTO(isit.get());

        }
    }

    @Override
    public String modifyDiary(DiaryDTO dto, List<TagDTO> tagList) {
        Diary originalDiary = repository.findByDino(dto.getDino());
        DiaryDTO getByDino = entityToDTO(originalDiary);

        originalDiary.getTags().forEach(tag -> {
            tagRepository.deleteById(tag.getTagId());
        });
        getByDino.setTitle(dto.getTitle());
        getByDino.setContent(dto.getContent());
        getByDino.setOpenYN(dto.isOpenYN());
        getByDino.setReplyYN(dto.isReplyYN());
        Diary modifiedDiary = dtoToEntity(getByDino);
        repository.save(modifiedDiary);

        // 태그가 있을때만 TagDTO를 Tag로
        if (tagList != null && tagList.size() > 0) {
            for (TagDTO i : tagList) {
                Tag tagResult = tagDTOtoEntity(i);
                tagResult.updateDiary(modifiedDiary);
                tagRepository.save(tagResult);
            }
        }
        return modifiedDiary.getDino().toString();
    }

    @Override
    public String deleteDiary(Long dino, String id) {
        // TODO Auto-generated method stub
        return null;
    }

    // 댓글 등록, 대댓글 등록
    @Override
    public String registerReply(ReplyDTO dto) {
        // Optional<Member> result = memberRepository.findById(dto.getId());
        // Optional<Reply> checkMember =
        // replyRepository.getReplyByDinoAndId(Diary.builder().dino(dto.getDino()).build(),
        // Member.builder().Id(dto.getId()).build());
        // if(!checkMember.isPresent()){
        // dto.setReplyContent(replyContent);
        // }
        return "임시";
    }

    // 댓글 수정
    @Override
    public String modifyReply(ReplyDTO dto, String id) {
        // TODO Auto-generated method stub
        return null;
    }
    // 댓글 삭제
    // @Override
    // public String deleteReply(ReplyDTO dto, String id) {
    // Optional<Reply> checkReply =
    // replyRepository.getReplyByRnoAndId(dto.getRno(), dto.getId());
    // if(checkReply.isPresent()){
    // replyRepository.delete(checkReply.get());
    // return "Deleted Successfully";
    // } else {
    // return "Could not Delete Reply";
    // }
    // }

    @Override
    public List<Object[]> getDiaryList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Object[]> getSearchDiaryList(String search) {
        // TODO Auto-generated method stub
        return null;
    }

}
