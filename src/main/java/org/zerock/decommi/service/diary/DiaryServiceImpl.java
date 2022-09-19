package org.zerock.decommi.service.diary;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    // @Override
    // public DiaryDTO checkBeforeDiaryModify(Long dino, String id) {
    // Optional<Diary> isit = repository.getDiaryByDinoAndId(dino, id);
    // if (!isit.isPresent()) {
    // return null;
    // } else {
    // DiaryDTO dto = entityToDTO(isit.get());
    // List<String>tagString = tagRepository.getTagListByDino(dto.getDino())
    // .stream().map(tagEntity -> tagEntity.getTagName())
    // .collect(Collectors.toList());
    // dto.setTags(tagString);
    // return dto;
    // }
    // }

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
    public String deleteDiary(DiaryDTO dto) {
        Optional<Diary> checkDiary = repository.getDiaryByDinoAndId(dto.getDino(), dto.getWriter());
        if (checkDiary.isPresent()) {
            repository.delete(checkDiary.get());
            return "성공적으로 삭제되었습니다.";
        } else {
            return "유효하지 않은 요청입니다.";
        }
    }

    // // 댓글 등록 //이해가 잘 가지 않음
    // @Override
    // public Long registerReply(ReplyDTO dto) {
    // Optional<Member> result = memberRepository.findById(dto.getId());
    // Optional<Reply> checkMember = replyRepository.getReplyByDinoAndId(
    // Diary.builder().dino(dto.getDino()).build(),
    // Member.builder().id(dto.getId()).build());
    // if (!checkMember.isPresent()) {
    // // Optional<List<Long>> lastestrg =
    // // replyRepository.getLastestReplyGroupWhereMatchWithDino(dto.getDino());
    // Long setrg = 1L; // set ReplyGroup = rg
    // // if (lastestrg.get().size() != 0) {
    // // setrg = lastestrg.get().get(0) + 1;
    // // }
    // dto.setReplyGroup(setrg);
    // dto.setReplyDepth(0L);
    // dto.setReplyOrder(0L);
    // dto.setId(result.get().getId());
    // Reply reply = replyDTOtoEntity(dto);
    // replyRepository.save(reply);
    // return -1L;
    // } else {
    // return checkMember.get().getRno();
    // }
    // }

    // // 댓글 수정
    // @Override
    // public String modifyReply(ReplyDTO dto, String id) {
    // // TODO Auto-generated method stub
    // return null;
    // }
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

    // @Override
    // public HashMap<String, Object> getReplyListByDino(Long dino, Pageable
    // pageable) {
    // Page<Reply> replyList = replyRepository.getPageList(pageable, dino);
    // if (!replyList.isEmpty()) {
    // List<ReplyDTO> dto = replyList.stream().map((Function<Reply, ReplyDTO>) rt ->
    // {
    // log.info(rt);
    // return replyEntityToDTO(rt);
    // }).collect(Collectors.toList());
    // HashMap<String, Object> result = new HashMap<>();
    // result.put("replyList", dto);
    // result.put("page", pageable.getPageNumber());
    // result.put("pageTotalCount", replyList.getTotalPages());
    // return result;
    // }
    // return null;
    // }

    // @Override
    // public HashMap<String, Object> getReplyListByDinoWithId(Long dino, Pageable
    // pageable, String id) {
    // Page<Reply> replyList = replyRepository.getPageList(pageable, dino);
    // if (!replyList.isEmpty()) {
    // List<ReplyDTO> dto = replyList.stream().map((Function<Reply, ReplyDTO>) rt ->
    // {
    // return replyEntityToDTO(rt);
    // }).collect(Collectors.toList());
    // // 댓글 신고부분 여기에 작성해야함
    // // =============================
    // HashMap<String, Object> result = new HashMap<>();
    // result.put("replyList", dto);
    // result.put("page", pageable.getPageNumber());
    // result.put("pageTotalCount", replyList.getTotalPages());
    // return result;
    // }
    // return null;
    // }

    // @Override
    // public List<Object[]> getDiaryList() {
    // return repository.getListAndAuthor();
    // }

    // @Override
    // public List<Object[]> getSearchDiaryList(String search) {
    // String decode = "";
    // try{
    // decode = URLDecoder.decode(search, "UTF-8");
    // }catch (Exception e){
    // e.printStackTrace();
    // }
    // return repository.getListAndAuthorByAuthorOrDtitle(decode);
    // }
}
