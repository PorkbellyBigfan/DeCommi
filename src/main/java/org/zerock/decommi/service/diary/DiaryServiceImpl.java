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
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.Tag;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.TagRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class DiaryServiceImpl implements DiaryService {
    private final DiaryRepository repository;
    private final TagRepository tagRepository;

    @Transactional
    @Override
    public Long register(DiaryDTO diaryDTO) {
        log.info("Register diary : " + diaryDTO);
        Map<String, Object> entityMap = dtoToEntity(diaryDTO);
        Diary diary = (Diary) entityMap.get("diary");
        List<Tag> tagList = (List<Tag>) entityMap.get("tagList");
        repository.save(diary);
        tagList.forEach(new Consumer<Tag>() {
            @Override
            public void accept(Tag tag) {
                tagRepository.save(tag);
            }
        });
        return diary.getDino();
    }

    @Override
    public void modify(DiaryDTO diaryDTO) {
        Optional<Diary> result = repository.findById(diaryDTO.getDino());
        if (result.isPresent()) {
            Diary diary = result.get();
            diary.changeTitle(diaryDTO.getTitle());
            diary.changeContent(diaryDTO.getContent());
            repository.save(diary);
        }
    }

    @Override
    public void delete(Long dino) {
        log.info("delete diary : " + dino);
        repository.deleteById(dino);
    }

    @Override
    public DiaryDTO getDiary(Long dino) {
        List<Object[]> result = repository.getDiaryWithAll(dino);
        Diary diary = (Diary) result.get(0)[0];
        List<Tag> tagList = new ArrayList();
        result.forEach(new Consumer<Object[]>() {
            @Override
            public void accept(Object[] arr) {
                Tag tag = (Tag) arr[1];
                tagList.add(tag);
            }
        });
        Long replyCnt = (Long) result.get(0)[2];
        Long heartCnt = (Long) result.get(0)[3];
        // Long bookmarkCnt = (Long) result.get(0)[4];
        // Long reportCnt = (Long) result.get(0)[5];
        return entityToDTO(diary, tagList, replyCnt, heartCnt);
    }

    @Override
    public PageResultDTO<DiaryDTO, Object[]> getPageList(PageRequestDTO req) {
        // 요청하는 페이지에 대한 정보를 가진 객체 Pageable
        Pageable pageable = req.getPageable(Sort.by("dino").descending());
        // 해당 페이지에 대한 정보를 가진 객체 Page
        Page<Object[]> result = repository.getListPage(pageable);
        Function<Object[], DiaryDTO> fn = new Function<Object[], DiaryDTO>() {
            @Override
            public DiaryDTO apply(Object[] t) {
                return entityToDTO(
                        (Diary) t[0],
                        (List<Tag>) (Arrays.asList((Tag) t[1])), // tagList
                        (Long) t[2], // replyCnt
                        (Long) t[3] // heartCnt
                // (Long) t[4], // bookmarkCnt
                // (Long) t[5] // reportCnt
                );
            }
        };
        return new PageResultDTO<>(result, fn);
    }
}
