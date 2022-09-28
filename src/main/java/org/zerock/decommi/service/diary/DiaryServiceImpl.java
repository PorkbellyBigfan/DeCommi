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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.FileDTO;
import org.zerock.decommi.dto.HeartDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.dto.ReplyDTO;
import org.zerock.decommi.dto.ReportDTO;
// import org.zerock.decommi.dto.SearchResultDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.File;
import org.zerock.decommi.entity.diary.Heart;
import org.zerock.decommi.entity.diary.QDiary;
import org.zerock.decommi.entity.diary.Reply;
import org.zerock.decommi.entity.diary.Report;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.entity.member.Bookmark;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.diary.BookmarkRepository;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.FileRepository;
import org.zerock.decommi.repository.diary.HeartRepository;
import org.zerock.decommi.repository.diary.ReplyRepository;
import org.zerock.decommi.repository.diary.ReportRepository;
import org.zerock.decommi.repository.diary.TagRepository;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.vo.DiaryPostList;
import org.zerock.decommi.vo.SearchCondition;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

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
    private final FileRepository fileRepository;
    private final HeartRepository heartRepository;
    private final BookmarkRepository bookmarkRepository;
    private final ReportRepository reportRepository;

    @Override
    public String registerDiary(DiaryDTO dto) {
        Diary result = dtoToEntity(dto);
        repository.save(result); // 여기서 dino 생성됨
        List<FileDTO> fileList = dto.getFileDTOList();
        fileList.forEach(new Consumer<FileDTO>() {
            @Override
            public void accept(FileDTO dto) {
                File file = fileDTOtoEntity(dto, result.getDino());
                fileRepository.save(file);
            }
        });
        log.info("result.getDino() : "+result.getDino());
        List<String> tagList = dto.getTagList();
        tagList.forEach(new Consumer<String>(){
            @Override
            public void accept(String dto) {
                Tag tag = tagDTOtoEntity(dto);
                tag.updateDiary(result);
                tagRepository.save(tag);
            }
        });
        // for (String i : tagList) {
        //     Tag tagResult = tagDTOtoEntity(i);
        //     tagResult.updateDiary(result);
        //     tagRepository.save(tagResult);
        // }
        
        return result.getDino().toString();
    }

    @Override
    public DiaryDTO checkBeforeDiaryModify(Long dino, String id) {
        Optional<Diary> isit = repository.getDiaryByDinoAndId(dino, id);
        if (!isit.isPresent()) {
            return null;
        } else {
            DiaryDTO dto = entityToDTO(isit.get());
            List<String> tagList = tagRepository.getList(dto.getDino())
                    .stream()
                    .map(tentity -> tentity.getTagName())
                    .collect(Collectors.toList());
            dto.setTagList(tagList);
            return dto;
        }
    }

    @Transactional
    @Override
    public String modifyDiary(DiaryDTO dto, List<String> tagList) {
        Diary originalDiary = repository.findByDino(dto.getDino());
        DiaryDTO getByDino = entityToDTO(originalDiary);

        originalDiary.getTagList().forEach(tag -> {
            tagRepository.deleteById(tag.getTagId());
        });
        getByDino.setTitle(dto.getTitle());
        getByDino.setContent(dto.getContent());
        getByDino.setOpenYN(dto.isOpenYN());
        getByDino.setReplyYN(dto.isReplyYN());
        Diary modifiedDiary = dtoToEntity(getByDino);
        repository.save(modifiedDiary);

        List<FileDTO> fileList = dto.getFileDTOList();
        fileList.forEach(new Consumer<FileDTO>() {
            @Override
            public void accept(FileDTO dto) {
                File file = fileDTOtoEntity(dto, modifiedDiary.getDino());
                fileRepository.save(file);
            }
        });

        // 태그가 있을때만 TagDTO를 Tag로
        for (String i : tagList) {
            Optional<Tag> tagTemp = tagRepository.findByDinoAndTagName(modifiedDiary, i);
            if (!tagTemp.isPresent()) {
                Tag tagResult = tagDTOtoEntity(i);
                tagResult.updateDiary(modifiedDiary);
                tagRepository.save(tagResult);
            }
        }
        return modifiedDiary.getDino().toString();
    }

    @Override
    public void deleteDiary(Long dino) {
        repository.deleteById(dino);
        repository.deleteFileByDino(dino);

    }

    @Transactional(readOnly = true)
    @Override
    public DiaryDTO getDiaryPostByDino(Long dino) {
        Diary result = repository.getByDino(dino);
        DiaryDTO dto = entityToDTO(result);
        List<String> tagString = tagRepository.getList(result.getDino())
                .stream()
                .map(tentity -> tentity.getTagName())
                .collect(Collectors.toList());
        dto.setTagList(tagString);
        return dto;
    }
    @Transactional(readOnly=true)

    @Override
    public List<DiaryPostList> getDiaryPostList() {
        Sort sort = sortByDino();
        List<DiaryPostList> result = repository.getList(sort).get().stream().map(v -> {
            return new DiaryPostList(v);
        }).collect(Collectors.toList());
        return result;
    }

    // @Override
    // public SearchResultDTO<DiaryPostList, Diary> getDiaryPostList(SearchCondition searchCondition) {
    //     Sort sort = sortByDino();
    //     BooleanBuilder booleanBuilder = getSearch(searchCondition);
    //     return new SearchResultDTO<>(result, fn);
    // }

    // private BooleanBuilder getSearch(SearchCondition searchCondition){
    //     String keyword = searchCondition.getKeyword();
    //     List<String>tagList = searchCondition.getTagList();

    //     BooleanBuilder booleanBuilder = new BooleanBuilder(); //쿼리를 질의하기 위한 객체
    //     QDiary qDiary = QDiary.diary; //관련된 쿼리 객체
    //     BooleanExpression expression = qDiary.dino.gt(0L); //게시글번호가 0 이상인것만 검색
    //     booleanBuilder.and(expression);
    //     BooleanBuilder conditionBuilder = new BooleanBuilder();
    //     if(tagList == null){
    //         conditionBuilder.or(qDiary.title.contains(keyword));
    //     }
    //     return booleanBuilder;

    // }


    // 하트
    @Override
    public String addHeart(HeartDTO dto) {
        Optional<Heart> checkHeart = heartRepository.checkHeartLogByMemberIdAndDiaryId(dto.getMid(), dto.getDino());
        Heart entity = heartDTOtoEntity(dto);
        if (checkHeart.isPresent()) {
            heartRepository.delete(checkHeart.get());
            return "좋아요 취소";
        } else {
            heartRepository.save(entity);
            return "좋아요";
        }
    }

    // 북마크
    @Override
    public String addBookmark(BookmarkDTO dto) {
        Optional<Bookmark> checkBookmark = bookmarkRepository.checkBookmarkLogByMemberIdAndDiary(dto.getMid(),
                dto.getDino());
        Bookmark entity = bookmarkDTOtoEntity(dto);
        if (checkBookmark.isPresent()) {
            bookmarkRepository.delete(checkBookmark.get());
            return "북마크 취소";
        } else {
            bookmarkRepository.save(entity);
            return "북마크 추가";
        }

    }

    // 신고
    @Override
    public String addDiaryReport(ReportDTO dto) {
        Optional<Report> checkReport = reportRepository.checkReportLogByMemberIdAndDiaryId(dto.getMid(), dto.getDino());
        if (checkReport.isPresent()) {
            return "이미 신고한 글입니다";
        } else {
            reportRepository.save(reportDTOtoEntity(dto));
            return "신고가 완료되었습니다";
        }

    }

    // 댓글 등록 //이해가 잘 가지 않음
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

    // 댓글 수정
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

    // @Transactional
    // @Override
    // public List<Object[]> getSearchDiaryList(String search) {
    // String decode = "";
    // try{
    // decode = URLDecoder.decode(search, "UTF-8");
    // }catch (Exception e){
    // e.printStackTrace();
    // }
    // return repository.getListByTitleOrContent(decode);
    // }

    private Sort sortByDino() {
        return Sort.by(Sort.Direction.DESC, "dino");
    }
}
