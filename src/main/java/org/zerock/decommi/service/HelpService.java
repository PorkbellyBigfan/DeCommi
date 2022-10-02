package org.zerock.decommi.service;

import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.Help;
import org.zerock.decommi.entity.member.Member;

public interface HelpService {
    Long register(HelpDTO dto);
    PageResultDTO<HelpDTO,Help>getNoticeList(PageRequestDTO requestDTO);
    PageResultDTO<HelpDTO,Help>getQnAList(PageRequestDTO requestDTO);
    HelpDTO read (Long hbno);
    void modifyHelp (HelpDTO dto);
    void deleteHelp(Long hbno);

    default Help dtoToEntity (HelpDTO dto){
        Help help = Help.builder()
            .hbno(dto.getHbno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(Member.builder().mid(dto.getWriter()).build())
            .helpType(dto.getHelpType())
            .build();
        return help;
    }

    default HelpDTO entityToDTO (Help entity){
        HelpDTO dto = HelpDTO.builder()
            .hbno(entity.getHbno())
            .title(entity.getTitle())
            .content(entity.getContent())
            .helpType(entity.getHelpType())    
            .writer(entity.getWriter().getMid())
            .build();
        return dto;
    }
    
}
