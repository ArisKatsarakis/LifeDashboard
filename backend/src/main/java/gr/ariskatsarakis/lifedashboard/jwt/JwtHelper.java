package gr.ariskatsarakis.lifedashboard.jwt;

import java.security.Key;
import java.security.KeyFactory;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;;

/**
 * JwtHelper
 */
@Component
public class JwtHelper {
  public static final long JWT_TOKEN_DURATION = 5 * 60 * 60;
  private SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS512);

  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimResolver.apply(claims);
  }

  private Claims getAllClaimsFromToken(String token) {
    JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secret).build();
    return jwtParser.parseClaimsJws(token).getBody();
    // return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
  }

  public Boolean isTokenExpired(String token) {
    Date date = getClaimFromToken(token, Claims::getExpiration);
    return date.before(new Date());
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.getUsername());
  }

  public String doGenerateToken(Map<String, Object> claims, String subject) {
    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_DURATION * 1000))
        .signWith(secret, SignatureAlgorithm.HS512).compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
