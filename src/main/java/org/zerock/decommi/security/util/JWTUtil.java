package org.zerock.decommi.security.util;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {
    private String secretKey = "decommi123456789";
    private long expire = 60 * 24 * 30;

    public String generateToken(Long mid, String email) throws Exception {
        String result = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
                .claim("jti", mid)
                .claim("sub", email)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
                .compact();
        log.info(result);
        return result;
    }

    @SuppressWarnings("rawtypes") //Generic을 사용하는 클래스 매개변수가 불특정일 때의 경고를 무시
    public Boolean validateAndExtract(String tokenStr, String mid) throws Exception {
        Boolean checker = null;
        try {
            DefaultJws defaultjJws = (DefaultJws) Jwts.parser()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(tokenStr);

            DefaultClaims claims = (DefaultClaims) defaultjJws.getBody();
            String mid_check = claims.getId();
            if(Integer.parseInt(mid_check)== Integer.parseInt(mid)){
                checker = true;
            } else{
                checker = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return checker;
    }
}
