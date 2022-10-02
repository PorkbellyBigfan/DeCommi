package org.zerock.decommi.service.member;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.decommi.dto.MemberDTO;
import org.zerock.decommi.dto.PageRequestDTO;
import org.zerock.decommi.dto.PageResultDTO;
import org.zerock.decommi.entity.member.Member;
import org.zerock.decommi.repository.member.MemberRepository;
import org.zerock.decommi.vo.Findpw;
import org.zerock.decommi.vo.Setpw;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  private final MemberRepository repository;
  private final PasswordEncoder encoder;

  @Override
  public MemberDTO emailCheck(String email) {
    log.info("emailCheck   : " + email);
    MemberDTO dto = null; // 기존에 있던 dto 털어주기
    Optional<Member> result = repository.findByEmail(email);// 이메일이 기존에 있던 것인지 체크
    if (result.isPresent()) {
      Member member = result.get();
      dto = entityToDTO(member); // 만약 이메일이 존재한다면 기존의 데이터 가져오기
    }
    return dto;
  }


  @Override
  public MemberDTO getMemberDTO(String email) {
    MemberDTO dto = entityToDTO(repository.getReferenceById(email));
    return dto;
  }

  // 회원가입
  @Transactional
  @Override
  public String signUp(MemberDTO dto) {
    dto.setPw(encoder.encode(dto.getPw()));
    Member member = dtoToEntity(dto);
    repository.save(member);
    return member.getEmail();
  }

  @Override
  public List<MemberDTO> getList() {
    List<Member> result = repository.findAll();
    return result.stream().map(new Function<Member, MemberDTO>() {
      @Override
      public MemberDTO apply(Member t) {
        return entityToDTO(t);
      }
    }).collect(Collectors.toList());
  }

  @Override
  public PageResultDTO<MemberDTO, Member> getPageList(PageRequestDTO dto) {
    log.info("PageRequestDTO: " + dto);
    Pageable pageable = dto.getPageable(Sort.by("email"));
    Page<Member> result = repository.getPageList(pageable);
    Function<Member, MemberDTO> fn = new Function<Member, MemberDTO>() {
      @Override
      public MemberDTO apply(Member t) {
        return entityToDTO(t);
      }
    };
    return new PageResultDTO<>(result, fn);
  }

  @Override
  public Boolean findEmail(MemberDTO email) {
    Optional<Member> result = repository.findByEmail(email.getEmail());
    if (result.isEmpty()) {
      return false;
    }
    return true;
  }
  @Override
  public Long findPw(Findpw vo) {
    Long result = repository.findMidByEmailAndQ(vo.getEmail(), vo.getQ1(), vo.getQ2(), vo.getQ3());
    if (result == null) {
      return null;
    } else {
      return result;
    }
  }
  @Override
  public Boolean changePw(Setpw vo) {
    repository.changePwByMid(vo.getMid(), encoder.encode(vo.getPw()));
    return true;
  }

  @Override
  public Boolean pwCheck(String email, String pw) {
    Optional<Member> member = repository.findByEmail(email);
    if(encoder.matches(pw, member.get().getPw())){
      return true;
    }else{
      return false;
    }
  }
}
