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

        //이미지 업로드 구현되면 추가예정
        // List<ImageList> lists = dto.getImage();

        for(TagDTO i : tagList){
            Tag tagResult = tagDTOtoEntity(i);
            tagResult.updateDiary(result);
            tagRepository.save(tagResult);
        }
        return result.getDino().toString();
    }

    @Override
    public String modifyDiary(DiaryDTO dto, String email) {
    // TODO Auto-generated method stub
    return null;
    }

    @Override
    public String deleteDiary(Long dino, String email) {
    // TODO Auto-generated method stub
    return null;
    }


    //댓글 등록, 대댓글 등록
    // @Override
    // public String registerReply(ReplyDTO dto) {
    //     Optional<Member> result = memberRepository.findByEmail(dto.getEmail());
    //     Optional<Reply> checkMember = replyRepository.getReplyByDinoAndEmail(Diary.builder().dino(dto.getDino()).build(), Member.builder().email(dto.getEmail()).build());
    //     if(!checkMember.isPresent()){
    //         dto.setReplyContent(replyContent);
    //     }
    // }
    //댓글 수정
    @Override
    public String modifyReply(ReplyDTO dto, String email) {
    // TODO Auto-generated method stub
    return null;
    }
    //댓글 삭제    
    @Override
    public String deleteReply(ReplyDTO dto, String email) {
        Optional<Reply> checkReply = replyRepository.getReplyByRnoAndEmail(dto.getRno(), dto.getEmail());  
        if(checkReply.isPresent()){
            replyRepository.delete(checkReply.get());
            return "Deleted Successfully";
        } else {
            return "Could not Delete Reply";
        }
    }


    
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
