package cl.ms.kabeli.utilitarios;

import java.security.Key;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private final String KEY = "kalebitest";

	public boolean validateJWT(String jwt) {
		try {
			String jwtFixed = jwt.replace("Bearer ", "");
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwtFixed);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String generateJWT(String user) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(KEY);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		JwtBuilder builder = Jwts.builder().setId(user).setIssuedAt(now).setSubject("Kalebi").setIssuer("Prueba conceptual")
				.signWith(signatureAlgorithm, signingKey);
		if (nowMillis > 0) {
			long expMillis = nowMillis + 300000;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
}
