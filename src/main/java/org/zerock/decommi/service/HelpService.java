package org.zerock.decommi.service;

import java.util.stream.Collectors;

import javax.persistence.EnumType;

import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.Help;
import org.zerock.decommi.entity.HelpRole;
import org.zerock.decommi.entity.member.Member;

public interface HelpService {
    Long register(HelpDTO dto);
    PageResultDTO<HelpDTO,Help>getList(PageRequestDTO requestDTO);
    HelpDTO read (Long hbno);
    void modifyHelp (HelpDTO dto);
    void deleteHelp(Long hbno);

    default Help dtoToEntity (HelpDTO dto){
        Help help = Help.builder()
            .hbno(dto.getHbno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(Member.builder().mid(dto.getWriter()).build())
            .roleSet(dto.getRoleSet().stream().map(
                    t->{
                        if(t.equals("ROLE_NOTICE"))
                            return HelpRole.NOTICE;
                        else if(t.equals("ROLE_FQA"))
                            return HelpRole.FQA;
                        else
                            return HelpRole.NOTICE;
                    }).collect(Collectors.toSet()))
            .build();
        return help;
    }

    default HelpDTO entityToDTO (Help entity){
        HelpDTO dto = HelpDTO.builder()
            .hbno(entity.getHbno())
            .title(entity.getTitle())
            .content(entity.getContent())
            .roleSet(entity.getRoleSet().stream().map(
                role -> new String("ROLE_" + role.name()))
                .collect(Collectors.toSet()))            
            .writer(entity.getWriter().getMid())
            .build();
        return dto;
    }
    
}
