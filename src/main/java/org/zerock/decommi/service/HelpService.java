package org.zerock.decommi.service;

import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.Help;
import org.zerock.decommi.entity.member.Member;

public interface HelpService {
    Long Noticeregister(HelpDTO dto);

    Long FAQregister(HelpDTO dto);

    PageResultDTO<HelpDTO, Help> getNoticeList(PageRequestDTO requestDTO);

    PageResultDTO<HelpDTO, Help> getFAQList(PageRequestDTO requestDTO);

    HelpDTO read(Long hbno);

    void modifyHelp(HelpDTO dto);

    void deleteHelp(HelpDTO dto);

    default Help dtoToEntity(HelpDTO dto) {
        Help help = Help.builder()
                .hbno(dto.getHbno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(Member.builder().mid(dto.getWriter()).build())
                .helpType(dto.getHelpType())
                .build();
        return help;
    }

    default HelpDTO entityToDTO(Help entity) {
        HelpDTO dto = HelpDTO.builder()
                .hbno(entity.getHbno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .helpType(entity.getHelpType())
                .writer(entity.getWriter().getMid())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }

}
