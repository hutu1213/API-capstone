package project.apicapstone.sercurity.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(Jwts.class);

    private Long jwtExpiration = 86400000L;
    private String jwtSecret = "thisismysecrettoken";
    private String authHeader = "Authorization";
    private String tokenPrefix = "Bearer ";


    public String generateJwtToken(Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        Date now = new Date();
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("roles",userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + jwtExpiration))
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//        //.withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e1) {
            logger.error("Invalid JWT Signature: {}", e1.getMessage());
        } catch (ExpiredJwtException e2) {
            logger.error("JWT token is expired: {}", e2.getMessage());
        } catch (MalformedJwtException e3) {
            logger.error("****Invalid JWT Token: {}", e3.getMessage());
        } catch (IllegalArgumentException e4) {
            logger.error("JWT claims string is empty: {}", e4.getMessage());
        } catch (UnsupportedJwtException e5) {
            logger.error("JWT Token is not support: {}", e5.getMessage());
        }

        return false;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
        } catch (ExpiredJwtException ex) {
            throw ex;
        }
    }
    public String getJwtTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(authHeader);

        if(StringUtils.hasText(header) && header.startsWith(tokenPrefix))
            return header.substring(tokenPrefix.length(), header.length());

        return null;
//        String bearer = request.getHeader("Authorization");
//
//        if (bearer == null) {
//            return null;
//        }
//
//        return bearer.substring("Bearer ".length()).trim();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
