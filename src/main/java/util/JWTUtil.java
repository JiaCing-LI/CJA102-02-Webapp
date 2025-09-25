package util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import fftest.mem.model.MemVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
public class JWTUtil {
	private static final String SECRET_KEY = "MY_SECRET_KEY_123";
	// 寫進去application.properties
	public static String generateToken(MemVO mem) {
		long nowMillis = System.currentTimeMillis();// 1970-01-01
													// 00:00:00 UTC ms
		long expMillis = nowMillis + 1000 * 60 * 60;

		byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
		// StandardCharsets.UTF_8 Java 內建的常用編碼
		Key key = new SecretKeySpec(keyBytes,
				SignatureAlgorithm.HS256.getJcaName());
		// 加密演算法 HMAC-SHA256
		String token = Jwts.builder().setSubject("Member")
				.claim("memId", mem.getMemId())
				.claim("username", mem.getMemName())
				.setIssuedAt(new Date(nowMillis))
				.setExpiration(new Date(expMillis))
				.signWith(key, SignatureAlgorithm.HS256).compact();
		return token;
	}
	public static void main(String[] args) {

		System.out.println(System.currentTimeMillis() + 1000 * 60 * 60);

	}
}
