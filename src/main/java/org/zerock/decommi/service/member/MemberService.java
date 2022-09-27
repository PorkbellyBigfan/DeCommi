package org.zerock.decommi.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.entity.member.MemberRole;

public interface MemberService {

    MemberDTO getMemberDTO(String email); // MemberDTO 가져오기
    MemberDTO emailCheck(String email); // 이메일 체크
    // String findId(MemberDTO id); //아이디 찾기
    String signUp(MemberDTO dto); // 회원가입
    List<MemberDTO> getList(); // 멤버 리스트 조회

    

    PageResultDTO<MemberDTO, Member> getPageList(PageRequestDTO dto);
    // void removeUuid(String uuid); //파일 고유아이디 삭제

    default MemberDTO entityToDTO(Member member) {
        MemberDTO memberDTO = MemberDTO.builder()
                .mid(member.getMid())
                .id(member.getId())
                .pw(member.getPw())
                .email(member.getEmail())
                .fromSocial(member.isFromSocial())
                .auth(member.isAuth())
                .q1(member.getQ1())
                .q1(member.getQ2())
                .q1(member.getQ3())
                .roleSet(member.getRoleSet().stream().map(
                        role -> new String("ROLE_" + role.name()))
                        .collect(Collectors.toSet()))
                .regDate(member.getRegDate())
                .modDate(member.getModDate())
                .build();
        return memberDTO;
    }

    default Member dtoToEntity(MemberDTO dto) {
        Member member = Member.builder()
                .mid(dto.getMid())
                .id(dto.getId())    
                .pw(dto.getPw())
                .email(dto.getEmail())
                .fromSocial(dto.isFromSocial())
                .auth(dto.isAuth())
                .q1(dto.getQ1())
                .q1(dto.getQ2())
                .q1(dto.getQ3())
                .roleSet(dto.getRoleSet().stream().map(
                        t -> {
                            if (t.equals("ROLE_MEMBER"))
                                return MemberRole.MEMBER;
                            else if (t.equals("ROLE_ADMIN"))
                                return MemberRole.ADMIN;
                            else
                                return MemberRole.MEMBER;
                        }).collect(Collectors.toSet()))
                .build();
        return member;
    }
}
