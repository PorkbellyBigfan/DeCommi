package org.zerock.decommi.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAUpdateClause;

import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPADeleteClause;

import org.zerock.decommi.admin.repository.AdminMemRepository;
import org.zerock.decommi.admin.repository.AdminReportRepository;
import org.zerock.decommi.admin.repository.AdminRepository;
import org.zerock.decommi.admin.dto.PageRequestDTO;
import org.zerock.decommi.admin.dto.PageResultDTO;
import org.zerock.decommi.dto.DiaryDTO;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.ReportDTO;
import org.zerock.decommi.dto.TagDTO;
import org.zerock.decommi.entity.diary.Diary;
import org.zerock.decommi.entity.diary.QDiary;
import org.zerock.decommi.entity.diary.QReply;
import org.zerock.decommi.entity.diary.QReport;
import org.zerock.decommi.entity.diary.QTag;
import org.zerock.decommi.entity.diary.Report;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.entity.member.QAlarm;
import org.zerock.decommi.entity.member.QMember;
import org.zerock.decommi.service.diary.DiaryService;
import org.zerock.decommi.service.member.MemberService;
// import org.zerock.decommi.repository.diary.TagRepository;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adRepository;
    private final AdminMemRepository admRepository;
    private final AdminReportRepository adrRepository;
    private final DiaryService dService;
    private final MemberService mService;

    @PersistenceContext
    EntityManager em;

    // ???????????? ?????????

    @Override
    public PageResultDTO<DiaryDTO, Diary> getDiaryListWithTagAndReply(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("dino").ascending());
        BooleanBuilder booleanBuilder = getDiarySearch(requestDTO);
        Page<Diary> result = adRepository.findAll(booleanBuilder, pageable);
        Function<Diary, DiaryDTO> fn = entity -> dService.entityToDTO(entity);
        return new PageResultDTO<>(result, fn);

    }

    // ???????????? ??????
    @Transactional
    @Override
    public Boolean diaryDelete(DiaryDTO dto) {
        QDiary qDiary = new QDiary("diary");
        QTag qTag = new QTag("tag");
        QReply qReply = new QReply("reply");
        QAlarm qAlarm = new QAlarm("alarm");
        QReport qReport = new QReport("report");

        JPADeleteClause deleteClause = new JPADeleteClause(em, qDiary);
        JPADeleteClause deleteReport = new JPADeleteClause(em, qReport);
        JPADeleteClause deleteTag = new JPADeleteClause(em, qTag);
        JPADeleteClause deleteReply = new JPADeleteClause(em, qReply);
        JPADeleteClause deleteAlarm = new JPADeleteClause(em, qAlarm);

        deleteReply.where(qReply.dino.dino.eq(dto.getDino())).execute();
        deleteReport.where(qReport.dino.dino.eq(dto.getDino())).execute();
        deleteAlarm.where(qAlarm.diary.dino.eq(dto.getDino())).execute();
        deleteTag.where(qTag.dino.dino.eq(dto.getDino())).execute();
        deleteClause.where(qDiary.dino.eq(dto.getDino())).execute();
        return true;
    }

    // ???????????? ??????
    private BooleanBuilder getDiarySearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();
        QDiary qDiary = QDiary.diary;

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qDiary.dino.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditBuilder = new BooleanBuilder();
        if (type.contains("title")) {
            conditBuilder.or(qDiary.title.contains(keyword));
        }
        ;

        if (type.contains("content")) {
            conditBuilder.or(qDiary.content.contains(keyword));
        }
        ;
        booleanBuilder.and(conditBuilder);
        return booleanBuilder;
    }

    // ?????? ?????????
    @Override
    public PageResultDTO<MemberDTO, Member> getUserList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mid").ascending());
        BooleanBuilder booleanBuilder = getUserSearch(requestDTO);
        Page<Member> result = admRepository.findAll(booleanBuilder, pageable);
        Function<Member, MemberDTO> fn = entity -> mService.entityToDTO(entity);
        return new PageResultDTO<>(result, fn);
    }

    // ?????? ??????
    private BooleanBuilder getUserSearch(PageRequestDTO requestDTO) {
        String type = requestDTO.getType();
        String keyword = requestDTO.getKeyword();

        QMember qMember = QMember.member;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qMember.mid.gt(0L);
        booleanBuilder.and(expression);
        if (type == null || type.trim().length() == 0) {
            return booleanBuilder;
        }

        BooleanBuilder conditBuilder = new BooleanBuilder();
        if (type.contains("id")) {
            conditBuilder.or(qMember.id.contains(keyword));
        }
        ;

        if (type.contains("mail")) {
            conditBuilder.or(qMember.email.contains(keyword));
        }
        ;
        booleanBuilder.and(conditBuilder);
        return booleanBuilder;
    }

    // ?????? ??????
    @Override
    @Transactional
    public Boolean UserDeleter(MemberDTO dto) {
        // QMember mmbr = new QMember("mmbr");
        QReport qReport = new QReport("report");

        // JPADeleteClause deleteUser = new JPADeleteClause(em, mmbr);
        JPADeleteClause deleteReport = new JPADeleteClause(em, qReport);
        Optional<List<Diary>> diarylist = adRepository.getDiaryListByUserid(dto.getId());
        if (diarylist.isPresent()) {
            diarylist.get().forEach(v -> {
                diaryDelete(dService.entityToDTO(v));
            });
        }
        // admRepository.findById(dto.getMid()).get().getRoleSet().remove(admRepository.findById(dto.getMid()).get().getRoleSet().toArray());
        deleteReport.where(qReport.mid.mid.eq(dto.getMid())).execute();
        admRepository.deleteById(dto.getMid());
        // admRepository.delete();
        // adRoleRepository.delete(admRepository.findById(dto.getMid()).get().getRoleSet());
        // deleteUser.where(mmbr.mid.eq(dto.getMid())).execute();
        return true;
    }

    // ?????? ??????
    @Override
    @Transactional
    public Boolean UserModifier(MemberDTO dto) {
        QMember mmbr = new QMember("mmbr");
        JPAUpdateClause updateClause = new JPAUpdateClause(em, mmbr);

        updateClause.where(mmbr.mid.eq(dto.getMid()))
                .set(mmbr.mid, dto.getMid())
                .set(mmbr.id, dto.getId())
                .set(mmbr.email, dto.getEmail())
                .set(mmbr.q1, dto.getQ1())
                .set(mmbr.q2, dto.getQ2())
                .set(mmbr.q3, dto.getQ3())
                .set(mmbr.auth, dto.isAuth())
                .execute();
        return true;
    }

    // ?????? ?????????
    // @Override
    // public List<String> getTag(Long dino) {
    // Optional<List<String>> hashs = tagRepository.OptionalfindByDiarys(dino);
    // if (hashs.isPresent()) {
    // List<String> result = hashs.get();
    // return result;
    // } else {
    // return null;
    // }
    // }

    // ?????? ??????
    @Override
    @Transactional
    public Boolean tagModify(TagDTO dto) {
        QTag tag = new QTag("tag");
        JPAUpdateClause updateClause = new JPAUpdateClause(em, tag);

        updateClause.where(tag.tagId.eq(dto.getTagId()))
                .set(tag.tagId, dto.getTagId())
                .set(tag.tagName, dto.getTagName())
                .execute();
        return true;
    }

    // ?????? ??????
    @Transactional
    @Override
    public Boolean tagDelete(TagDTO dto) {
        QTag qTag = new QTag("tag");
        JPADeleteClause deleteTag = new JPADeleteClause(em,
                qTag);
        deleteTag.where(qTag.tagId.eq(dto.getTagId())).execute();
        return true;
    }

    // ?????? ?????????
    @Override
    public PageResultDTO<ReportDTO, Report> getReportList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("mid_mid").ascending());
        QReport qReport = QReport.report;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        BooleanExpression expression = qReport.reid.gt(0L);
        booleanBuilder.and(expression);
        Page<Report> result = adrRepository.findAll(booleanBuilder, pageable);
        Function<Report, ReportDTO> fn = entity -> dService.reportEntitytoDTO(entity);
        return new PageResultDTO<>(result, fn);
    }

    // ?????? ??? ??????
    @Transactional
    @Override
    public Boolean reportDelete(ReportDTO dto) {
        QReport qReport = new QReport("report");
        JPADeleteClause deleteReport = new JPADeleteClause(em,
                qReport);
        deleteReport.where(qReport.reid.eq(dto.getReid())).execute();
        return true;
    }

    // ????????? ??????
    @Override
    public Boolean adminChecker(Long mid) {
        return admRepository.Admincheck(mid);
    }
}
