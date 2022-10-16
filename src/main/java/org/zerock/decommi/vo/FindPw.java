package org.zerock.decommi.vo;

import lombok.Data;

@Data
public class FindPw {
    String email;
    Long mid;
    String q1;
    String q2;
    String q3;
    String changePw;
}
