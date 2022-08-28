package org.zerock.decommi.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String email;
    private String pw;
    private String name;
    private String mobile;
    private boolean fromSocial;

    private LocalDateTime regDate, modDate;

    @Builder.Default
    private Set<String> roleSet = new HashSet<>();
}
