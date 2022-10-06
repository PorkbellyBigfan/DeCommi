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
        log.info("result.getDino() : " + result.getDino());
        List<String> tagList = dto.getTagList();
        tagList.forEach(new Consumer<String>() {
            @Override
            public void accept(String dto) {
                Tag tag = tagDTOtoEntity(dto);
                tag.updateDiary(result);
                tagRepository.save(tag);
            }
        });
        return result.getDino().toString();
    }

    @Override
    public DiaryDTO checkBeforeDiaryModify(Long dino, String id) {
        Optional<Diary> isit = repository.getDiaryByDinoAndId(dino, id);
        if (!isit.isPresent()) {
            return null;
        } else {
            DiaryDTO dto = entityToDTO(isit.get());
            List<String> tagList = tagRepository.getList(Diary.builder().dino(dto.getDino()).build())
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

    @Transactional
    @Override
    public Boolean deleteDiary(DiaryDTO dto) {
        Optional<Diary> check = repository.getDiaryByDinoAndId(dto.getDino(), dto.getWriter());
        log.info(dto);
        log.info(check);
        if (check.isPresent()) {
            repository.deleteById(dto.getDino());
            repository.deleteFileByDino(dto.getDino());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public DiaryDTO getDiaryPostByDino(Long dino) {
        Diary result = repository.getByDino(dino);
        DiaryDTO dto = entityToDTO(result);
        List<String> tagString = tagRepository.getList(Diary.builder().dino(result.getDino()).build())
                .stream()
                .map(tentity -> tentity.getTagName())
                .collect(Collectors.toList());
        dto.setTagList(tagString);
        return dto;
    }

    @Transactional
    @Override
    public PageResultDTO<DiaryDTO, Diary> getDiaryPostList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("dino").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Diary> result = repository.findAll(booleanBuilder, pageable);
        Function<Diary, DiaryDTO> fn = new Function<Diary, DiaryDTO>() {
            @Override
            public DiaryDTO apply(Diary t) {
                return entityToDTO(t);
            }
        };
        return new PageResultDTO<>(result, fn);
    }

    @Transactional
    @Override
    public PageResultDTO<DiaryDTO, Diary> getDiaryPostListByTagName(PageRequestDTO requestDTO, String tagName) {
        Pageable pageable = requestDTO.getPageable(Sort.by("dino").descending());
        BooleanBuilder booleanBuilder = getSearchByTagName(requestDTO, tagName);
        Page<Diary> result = repository.findAll(booleanBuilder, pageable);
        Function<Diary, DiaryDTO> fn = new Function<Diary, DiaryDTO>() {
            @Override
            public DiaryDTO apply(Diary t) {
                return entityToDTO(t);
            }
        };
        return new PageResultDTO<>(result, fn);
    }

    // 하트
    @Override
    public Boolean addHeart(HeartDTO dto) {
        Optional<Heart> checkHeart = heartRepository.checkHeartLogByMemberIdAndDiaryId(dto.getMid(), dto.getDino());
        Heart entity = heartDTOtoEntity(dto);
        if (checkHeart.isPresent()) {
            heartRepository.delete(checkHeart.get());
            return false;
        } else {
            heartRepository.save(entity);
            return true;
        }
    }

    // 북마크
    @Override
    public Boolean addBookmark(BookmarkDTO dto) {
        Optional<Bookmark> checkBookmark = bookmarkRepository.checkBookmarkLogByMemberIdAndDiary(dto.getMid(),
                dto.getDino());
        Bookmark entity = bookmarkDTOtoEntity(dto);
        if (checkBookmark.isPresent()) {
            bookmarkRepository.delete(checkBookmark.get());
            return false;
        } else {
            bookmarkRepository.save(entity);
            return true;
        }

    }

    // 신고
    @Override
    public Boolean addDiaryReport(ReportDTO dto) {
        Optional<Report> checkReport = reportRepository.checkReportLogByMemberIdAndDiaryId(dto.getMid(), dto.getDino());
        if (checkReport.isPresent()) {
            return false;
        } else {
            reportRepository.save(reportDTOtoEntity(dto));
            return true;
        }

    }

    // 댓글 등록
    @Override
    public String registerReply(ReplyDTO dto) {
        Optional<Member> result = memberRepository.findByMid(dto.getMid());
        Optional<List<Long>> lastestrg = replyRepository.getLastestReplyGroupWhereMatchWithDino(dto.getDino());
        // rno 안쓰는 이유는 대 댓글때문임.
        Long setrg = 1L; // set ReplyGroup = rg //처음 등록된 댓글은 setrg = 1L
        if (lastestrg.get().size() != 0) { // 처음 등록된 댓글이 아닐 경우
            setrg = lastestrg.get().get(0) + 1; // setrg += 1
        }
        dto.setReplyGroup(setrg);
        dto.setReplyDepth(0L); // 새댓글이라서 뎁스0
        dto.setReplyOrder(0L);
        dto.setMid(result.get().getMid());
        Reply reply = replyDTOtoEntity(dto);
        replyRepository.save(reply);
        log.info("reply rno " + reply.getRno());
        log.info("reply.getReplyContent()" + reply.getReplyContent());
        log.info("reply.getReplyDepth()" + reply.getReplyDepth());
        log.info("reply.getReplyGroup()" + reply.getReplyGroup());
        log.info("reply.getReplyOrder()" + reply.getReplyOrder());
        return reply.getDino().toString();
    }

    // 대댓글
    @Override
    public Long addNewReply(ReplyDTO dto) {
        Optional<Member> result = memberRepository.findByMid(dto.getMid());
        dto.setReplyGroup(dto.getReplyGroup());
        dto.setReplyDepth(dto.getReplyDepth());
        dto.setReplyOrder(dto.getReplyOrder());
        dto.setMid(result.get().getMid());
        Reply entity = replyDTOtoEntity(dto);
        log.info("대댓글 사용자 입력값 :::::" + dto);
        log.info("대댓글 entity :::::" + entity);
        log.info("entity.getRno()" + entity.getRno());
        log.info("entity.getReplyGroup()" + entity.getReplyGroup());
        log.info("entity.getReplyDepth()" + entity.getReplyDepth());
        replyRepository.save(entity);
        return entity.getRno();
    }

    // 수정
    @Override
    public String modifyReply(ReplyDTO dto) {
        // Optional<Member> result = memberRepository.findById(dto.getMid());
        Optional<Reply> checkReply = replyRepository.getReplyByRnoAndMid(dto.getRno(), dto.getMid());
        log.info("modify...." + dto);
        if (checkReply.isPresent()) {
            Reply reply = checkReply.get();
            reply.changeReplyContent(dto.getReplyContent());
            replyRepository.save(reply);
            return "수정";
        } else {
            return "실패";
        }
    }

    // 댓글 삭제
    @Override
    @Transactional
    public String deleteReply(ReplyDTO dto) {
        log.info("service dto --=-----------------------" + dto);
        Optional<Reply> checkReply = replyRepository.getReplyByRnoAndMid(dto.getRno(), dto.getMid());
        log.info("service checkReply =======================================" + checkReply);
        if (checkReply.isPresent()) {
            replyRepository.delete(checkReply.get());
            return "Deleted Successfully";
        } else {
            return "Could not Delete Reply";
        }
    }

    // 댓글리스트
    @Override
    public HashMap<String, Object> getReplyListByDino(Long dino, Pageable pageable) {
        Page<Reply> replyList = replyRepository.getPageList(pageable, dino);
        if (!replyList.isEmpty()) {
            List<ReplyDTO> dto = replyList.stream().map((Function<Reply, ReplyDTO>) rt -> {
                log.info(rt);
                return replyEntityToDTO(rt);
            }).collect(Collectors.toList());
            HashMap<String, Object> result = new HashMap<>();
            result.put("replyList", dto);
            result.put("page", pageable.getPageNumber());
            result.put("pageTotalCount", replyList.getTotalPages());
            return result;
        }
        return null;
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        log.info("service class ::: requestDTO 에서 보내준 type:::" + type);
        String keyword = requestDTO.getKeyword();
        log.info("service class ::: requestDTO 에서 보내준 keyword:::" + keyword);
        List<String> tagList = requestDTO.getTagList();
        log.info("service class ::: requestDTO 에서 보내준 tagList:::" + tagList);
        QDiary qDiary = QDiary.diary;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qDiary.dino.gt(0L).and(qDiary.openYN.isTrue());
        log.info(expression);
        booleanBuilder.and(expression);
        log.info(booleanBuilder);
        // ====================================================================================
        // = 여기까진 문제 없음 =
        // ====================================================================================
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if (type.contains("s")) { // "t" stand for Tag
            conditionBuilder
                    .or(qDiary.title.contains(keyword))
                    .or(qDiary.content.contains(keyword));
            // 여기서부터 문제인데
            // tagList란 사용자가 브라우저에서 추가한 태그리스트들인데 타입은 List<String>이다
            // List<String> 인 tagList 가 qDiary.tagList에 같은 이름을 가지고 있는 tag들이 있는지 확인하는 과정에서
            // 타입때문에 문제가 발생한다.
            // 이를 해결하기 위해
            // Cosumer를 사용했는데 Consumer는 객체를 forEach에 전달하여 List의 모든 아이템을 순회할 수 있다.
            // 문자열 t와 같은 Tag 테이블에 있는 태그들을 Optioanl<Tag>를 temp라는 임시 변수에 저장한다.
            // 이는 tagList에 들어있는 문자열들을 모두 순회할때까지 시행된다.
            // ex) tagList에 5개의 태그가 들어있다면 5번 실행될 것이다.
            // 각각의 실행마다 t라는 문자열을 가진 Optional<Tag> 객체가 실제 DB에 존재하는지 확인하고 만약 존재한다면
            // conditionalBuilder에 조건을 추가할 것이다.
            // ================================================================================================================
            tagList.forEach(new Consumer<String>() {
                @Override
                public void accept(String t) {
                    // 여기서 가져온 객체는 Tag의 pk인 tag_id를 반환하지 않을까? 실제 문자열이
                    // contains(temp.get().getTagName())이 되어야 정상이아닌가?
                    // 그전에 그렇게 할거면 이렇게 삥 둘러올 필요가 없었을 것이다.
                    // 애초에 그것이 가능하다면 qDiary.tagList.contains(tagList.stream().map()~~~) 으로 해결이 가능
                    // 했었을것.
                    Optional<Tag> temp = tagRepository.findByTagName(t);
                    if (temp.isPresent()) {
                        conditionBuilder.and(qDiary.tagList.contains(temp.get()));
                    }
                }
            });
        }
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    private BooleanBuilder getSearchByTagName(PageRequestDTO requestDTO, String tagName) {
        QDiary qDiary = QDiary.diary;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Optional<Tag> temp = tagRepository.findByTagName(tagName);
        BooleanExpression expression = qDiary.dino.gt(0L).and(qDiary.openYN.isTrue())
                .and(qDiary.tagList.contains(temp.get()));
        booleanBuilder.and(expression);
        return booleanBuilder;
    }
}
