package org.zerock.decommi.service.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.entity.diary.Heart;
import org.zerock.decommi.repository.diary.DiaryRepository;
import org.zerock.decommi.repository.diary.HeartRepository;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.service.member.MemberService;
import org.zerock.decommi.service.diary.DiaryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService{
    private final HeartRepository heartRepository;
    private final DiaryRepository diaryRepository;
    private final DiaryService diaryService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Override
    public HashMap<String, Object> getListDino(Long dino) {
        List<MemberDTO> member = new ArrayList<>();
        
        List<DiaryDTO> result = heartRepository.getList(dino).get().stream() //문제시 수정
            .map((Function<? super Heart, ? extends DiaryDTO>)v->{ 
                DiaryDTO list = diaryService.entityToDTO(diaryRepository.getByDino(v.getDino()));
                member.add(memberService.entityToDTO(memberRepository.findByEmail(list.getWriter()).get()));
                return list;
            }).collect(Collectors.toList()); 
            
        List<String> membernames = member.stream().map((Function<MemberDTO, String>)v->{
            return v.getEmail();
        }).collect(Collectors.toList());

        HashMap<String,Object> hash = new HashMap<>();
        hash.put("diary", result);
        hash.put("membername", membernames);
        return hash;
    }
};
