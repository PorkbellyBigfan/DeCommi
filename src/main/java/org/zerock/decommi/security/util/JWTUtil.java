package org.zerock.decommi.security.util;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.log4j.Log4j2;

//토큰의 설계도와 비슷
@Log4j2
public class JWTUtil {
    private String secretKey = "decommi123456789";
    private long expire = 60 * 24 * 30;

    // 보안상의 문제를 해결하기위해 mid email
    public String generateToken(Long mid, String email) throws Exception {
        String result = Jwts.builder()
                .setIssuedAt(new Date())// 발행 기간
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))// 만료 기간
                .claim("jti", mid)// 토큰에 담겨있는 정보
                .claim("sub", email)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))// 토큰 기본 알고리즘
                .compact();// 압축
        log.info(result);
        return result;
    }

    @SuppressWarnings("rawtypes") // Generic을 사용하는 클래스 매개변수가 불특정일 때의 경고를 무시
    public Boolean validateAndExtract(String tokenStr, String mid) throws Exception {// 토큰확인과 토큰 정보 꺼내기
        Boolean checker = null;
        try {
            DefaultJws defaultjJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(tokenStr);

            DefaultClaims claims = (DefaultClaims) defaultjJws.getBody();//
            String mid_check = claims.getId();
            if (Integer.parseInt(mid_check) == Integer.parseInt(mid)) {// 스트링으로 되어있는 토큰은 인트로 다시 반환
                checker = true;
            } else {
                checker = false;
            } // 반환된 토큰 값 확인
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return checker;// 올바른값반환
    }
}
