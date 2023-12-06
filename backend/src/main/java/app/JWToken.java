package app;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWToken {

    private static final String JWT_CALLNAME_CLAIM = "sub";
    private static final String JWT_ROLE_CLAIM = "role";
    private static final String JWT_ISSUER_CLAIM = "iss";
    private static final String JWT_ACCOUNTID_CLAIM = "id";
    public static final String  JWT_ATTRIBUTE_NAME = "JWTokenInfo";
    private String callname;
    private Long accountId;
    private String role;

    public String encode(String passphrase, String issuer, int expiration) {
        Key key = getKey(passphrase);

        return Jwts.builder()
                .claim(JWT_CALLNAME_CLAIM,callname)
                .claim(JWT_ACCOUNTID_CLAIM, accountId)
                .claim(JWT_ROLE_CLAIM, role)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(key)
                .compact();
    }

    public static JWToken decode(String token, String issuer, String passphrase)
            throws ExpiredJwtException, MalformedJwtException {
        // Validate the token string and extract the claims
        Key key = getKey(passphrase);
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token);
        Claims claims = jws.getBody();

        if (!claims.get(JWT_ISSUER_CLAIM).toString().equals(issuer)) {
            throw new MalformedJwtException("Invalid issuer");
        }
        // build our token from the extracted claims
        return new JWToken(
                claims.get(JWT_CALLNAME_CLAIM).toString(),
                Long.valueOf(claims.get(JWT_ACCOUNTID_CLAIM).toString()),
                claims.get(JWT_ROLE_CLAIM).toString()
        );
    }

    private static Key getKey(String passphrase) {
        byte[] hmacKey = passphrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }
}
