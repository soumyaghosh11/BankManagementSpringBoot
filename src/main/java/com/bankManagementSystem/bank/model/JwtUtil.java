package com.bankManagementSystem.bank.model;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private static final SecretKey secretKey = Keys.hmacShaKeyFor("mySuperSecureJWTKeyThatIsAtLeast32Chars!".getBytes(StandardCharsets.UTF_8));
	
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
	}
	
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	        return claimsResolver.apply(claims);
	    }

	    public boolean validateToken(String token, UserDetails userDetails) {
	        return extractUsername(token).equals(userDetails.getUsername());
	    }
}
