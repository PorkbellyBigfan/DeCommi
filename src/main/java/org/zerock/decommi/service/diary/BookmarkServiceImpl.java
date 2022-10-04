package org.zerock.decommi.service.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.BookmarkDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.entity.member.Bookmark;
import org.zerock.decommi.repository.diary.BookmarkRepository;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.service.member.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryService diaryService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Override
    public List<BookmarkDTO> getBookFolderList(Long mid) {
        List<Bookmark> result = bookmarkRepository.getFolderList(mid);
        return result.stream().map(new Function<Bookmark, BookmarkDTO>() {
            @Override
            public BookmarkDTO apply(Bookmark t) {
                return EntityToDto(t);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public HashMap<String, Object> getListDino(Long dino, String bfolderName) {
        List<MemberDTO> member = new ArrayList<>();
        List<DiaryDTO> result = bookmarkRepository.getList(dino, bfolderName).get().stream()
                .map((Function<? super Bookmark, ? extends DiaryDTO>) v -> {
                    DiaryDTO list = diaryService.entityToDTO(diaryRepository.getByDino(v.getDino()));
                    member.add(memberService.entityToDTO(memberRepository.findByEmail(list.getWriter()).get()));
                    return list;
                }).collect(Collectors.toList());

        List<String> membernames = member.stream().map((Function<MemberDTO, String>) v -> {
            return v.getEmail();
        }).collect(Collectors.toList());

        HashMap<String, Object> hash = new HashMap<>();
        hash.put("diary", result);
        hash.put("membername", membernames);
        return hash;
    }
};
