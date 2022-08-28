package org.zerock.decommi.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.Diary;
import org.zerock.decommi.entity.Tag;
import org.zerock.decommi.repository.DiaryRepository;
import org.zerock.decommi.repository.TagRepository;

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
        Optional<Diary> result = repository.getDiaryWithWriter(dino);
        if (result.isPresent()) {
            return entityToDTO(result.get());
        }
        return null;
    }

    @Override
    public List<DiaryDTO> getDiaryList() {
        List<Diary> result = repository.findAll();
        return result.stream().map(new Function<Diary, DiaryDTO>() {
            @Override
            public DiaryDTO apply(Diary t) {
                return entityToDTO(t);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public PageResultDTO<DiaryDTO, Object[]> getPageList(PageRequestDTO req) {
        // TODO Auto-generated method stub
        return null;
    }
}
