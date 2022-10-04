package org.zerock.decommi.service;

import java.util.Optional;
import java.util.function.Function;

<<<<<<< HEAD
=======
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.HelpDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.springframework.data.domain.Sort;
import org.zerock.decommi.entity.Help;
<<<<<<< HEAD
=======
import org.zerock.decommi.entity.QHelp;
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
import org.zerock.decommi.repository.HelpRepository;
import org.zerock.decommi.repository.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
<<<<<<< HEAD
public class HelpServiceImpl implements HelpService{
=======
public class HelpServiceImpl implements HelpService {
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
    private final HelpRepository helpRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long register(HelpDTO dto) {
<<<<<<< HEAD
        log.info("register..."+dto);
=======
        log.info("register..." + dto);
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
        Help help = dtoToEntity(dto);
        helpRepository.save(help);
        return help.getHbno();
    }

    @Override
    public HelpDTO read(Long hbno) {
<<<<<<< HEAD
        Optional<Help> result = helpRepository.findById(hbno);//??
        return result.isPresent()?entityToDTO(result.get()):null;
    }

    @Override
    public PageResultDTO<HelpDTO,Help> getNoticeList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("hbno").descending());
        Page<Help> result = helpRepository.getNoticeList(pageable);
        Function<Help, HelpDTO> fn = (entity->entityToDTO(entity));
=======
        Optional<Help> result = helpRepository.findById(hbno);// ??
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public PageResultDTO<HelpDTO, Help> getNoticeList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("hbno").descending());
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page < Help > result = helpRepository.getNoticeList(pageable,booleanBuilder);
        Function<Help, HelpDTO> fn = (entity -> entityToDTO(entity));
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<HelpDTO, Help> getQnAList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("hbno").descending());
<<<<<<< HEAD
        Page<Help> result = helpRepository.getQnAList(pageable);
        Function<Help, HelpDTO> fn = (entity->entityToDTO(entity));
=======
        BooleanBuilder booleanBuilder = getSearch(requestDTO);
        Page<Help> result = helpRepository.getQnAList(pageable,booleanBuilder);
        Function<Help, HelpDTO> fn = (entity -> entityToDTO(entity));
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
        return new PageResultDTO<>(result, fn);
    }

    @Override
<<<<<<< HEAD
    public void deleteHelp(Long hbno) {
        log.info(hbno);
        helpRepository.deleteById(hbno);

=======
    public void deleteHelp(HelpDTO dto) {
        Optional<Help> checkHelp = helpRepository.getHelpByMid(dto.getWriter(), dto.getHbno());
        if (checkHelp.isPresent()) {
            log.info("delete....." + dto);
            helpRepository.delete(checkHelp.get());
        }
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
    }

    @Override
    public void modifyHelp(HelpDTO dto) {
        log.info("modify...." + dto);
<<<<<<< HEAD
        Optional<Help> result = helpRepository.findById(dto.getHbno());
        if(result.isPresent()){
            Help help = result.get();
=======
        Optional<Help> checkHelp = helpRepository.getHelpByMid(dto.getWriter(), dto.getHbno());
        if (checkHelp.isPresent()) {
            Help help = checkHelp.get();
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
            help.changTitle(dto.getTitle());
            help.changContent(dto.getContent());
            helpRepository.save(help);
        }
<<<<<<< HEAD
    }

    
=======

        }
        private BooleanBuilder getSearch (PageRequestDTO requestDTO){// Querydsl 처리
            String type = requestDTO.getType();
            String keyword = requestDTO.getKeyword();

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            QHelp qHelp = QHelp.help;// 관련된 테이블에 대한 쿼리 객체
            BooleanExpression expression = qHelp.hbno.gt(0L);// gno>0 조건만 생성
            booleanBuilder.and(expression);
            if (type == null || type.trim().length() == 0)
                return booleanBuilder;// 검색 조건이 없는 경우

            // 검색 조건 작성
            BooleanBuilder conditionBuilder = new BooleanBuilder();
            if (type.contains("t"))
                conditionBuilder.or(qHelp.title.contains(keyword));
            if (type.contains("c"))
                conditionBuilder.or(qHelp.content.contains(keyword));
            if (type.contains("w"))
                conditionBuilder.or(qHelp.writer.email.contains(keyword));
            booleanBuilder.and(conditionBuilder); // 모든 조건 통합

            return booleanBuilder;
    }
>>>>>>> 6545e88baf82b73847e9f828c1e0334f3f393a8d
}
