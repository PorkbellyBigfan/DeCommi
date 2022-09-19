package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.zerock.decommi.entity.diary.Reply;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long mid;
    private String id;
    private String pw;
    private String email;
    // private String name;
    // private String mobile;
    private String q1;
    private String q2;
    private String q3;
    private boolean auth;
    private boolean fromSocial;
    private List<Reply> replyList;

    // 여기에 선호태그 비선호태그 넣으면 될것 같음

    private LocalDateTime regDate, modDate;

    @Builder.Default
    private Set<String> roleSet = new HashSet<>();
    @Builder.Default
    private List<TagDTO> likeTagDTOList = new ArrayList<>();
    @Builder.Default
    private List<TagDTO> dislikeTagDTOList = new ArrayList<>();

}
