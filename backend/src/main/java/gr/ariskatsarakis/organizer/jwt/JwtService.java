package gr.ariskatsarakis.organizer.jwt;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
        @Value("${security.jwt.secret-key}")
        private String secret;

        @Value("${security.jwt.expiration-time}")
        private Long jwtExpirestime;

        public String generateToken(String email) {
                String jwt = Jwts.builder()
                                .setSubject(email)
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirestime))
                                .signWith(getSignInKey())
                                .compact();
                return jwt;
        }

        String getUserNameFromToken(String token) {

                return Jwts.parserBuilder()
                                .setSigningKey(getSignInKey())
                                .build()
                                .parseClaimsJws(token)
                                .getBody()
                                .getSubject();

        }

        private SecretKey getSignInKey() {
                byte[] keyBytes = Decoders.BASE64.decode(this.secret);
                return Keys.hmacShaKeyFor(keyBytes);
        }

        public boolean validateJwtToken(String token) throws Exception {
                try {
                        Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
                        return true;
                } catch (SecurityException e) {
                        System.out.println("Invalid JWT signature: " + e.getMessage());
                        throw e;
                } catch (MalformedJwtException e) {
                        System.out.println("Invalid JWT token: " + e.getMessage());
                        throw e;
                } catch (ExpiredJwtException e) {
                        System.out.println("JWT token is expired: " + e.getMessage());
                        throw new Exception(e.getMessage());
                } catch (UnsupportedJwtException e) {
                        System.out.println("JWT token is unsupported: " + e.getMessage());
                        throw e;
                } catch (IllegalArgumentException e) {
                        System.out.println("JWT claims string is empty: " + e.getMessage());
                        throw e;
                }
        }
}
