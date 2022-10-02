package org.zerock.decommi.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.springframework.data.domain.Sort;
import org.zerock.decommi.entity.Help;
import org.zerock.decommi.repository.HelpRepository;
import org.zerock.decommi.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class HelpServiceImpl implements HelpService {
    private final HelpRepository helpRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long register(HelpDTO dto) {
        log.info("register..." + dto);
        Help help = dtoToEntity(dto);
        helpRepository.save(help);
        return help.getHbno();
    }

    @Override
    public HelpDTO read(Long hbno) {
        Optional<Help> result = helpRepository.findById(hbno);// ??
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public PageResultDTO<HelpDTO, Help> getNoticeList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("hbno").descending());
        Page<Help> result = helpRepository.getNoticeList(pageable);
        Function<Help, HelpDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<HelpDTO, Help> getQnAList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("hbno").descending());
        Page<Help> result = helpRepository.getQnAList(pageable);
        Function<Help, HelpDTO> fn = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public void deleteHelp(Long hbno) {
        log.info("delete....." + hbno);
        helpRepository.deleteById(hbno);

    }

    @Override
    public void modifyHelp(HelpDTO dto) {
        log.info("modify...." + dto);
        Optional<Help> result = helpRepository.findById(dto.getHbno());
        if (result.isPresent()) {
            Help help = result.get();
            help.changTitle(dto.getTitle());
            help.changContent(dto.getContent());
            helpRepository.save(help);
        }
    }

}
