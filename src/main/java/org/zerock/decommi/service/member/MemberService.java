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

    String signUp(MemberDTO dto); // 회원가입

    List<MemberDTO> getList(); // 멤버 리스트 조회

    PageResultDTO<MemberDTO, Member> getPageList(PageRequestDTO dto);
    // void removeUuid(String uuid); //파일 고유아이디 삭제

    default MemberDTO entityToDTO(Member member) {
        MemberDTO memberDTO = MemberDTO.builder()
                .email(member.getEmail())
                .mobile(member.getMobile())
                .fromSocial(member.isFromSocial())
                .auth(member.isAuth())
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
                .email(dto.getEmail())
                .pw(dto.getPw())
                .mobile(dto.getMobile())
                .fromSocial(dto.isFromSocial())
                .auth(dto.isAuth())
                .roleSet(dto.getRoleSet().stream().map(
                        t -> {
                            if (t.equals("ROLE_GUEST"))
                                return MemberRole.GUEST;
                            else if (t.equals("ROLE_MEMBER"))
                                return MemberRole.MEMBER;
                            else if (t.equals("ROLE_ADMIN"))
                                return MemberRole.ADMIN;
                            else
                                return MemberRole.GUEST;
                        }).collect(Collectors.toSet()))
                .build();
        return member;
    }
}
