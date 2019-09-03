package com.test.demo.publicres.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken
 * @author Tian yunfeng
 * @date 2018-09-18
 */
public class JwtToken {

	// 密钥
	private static final String SECRET = "secret";

	// jackson
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * header数据
	 * 
	 * @return
	 */
	private static Map<String, Object> createHead() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typ", "JWT");
		map.put("alg", "HS256");
		return map;
	}

	/**
	 * 生成token
	 *
	 * @param obj    对象数据
	 * @param maxAge 有效期
	 * @param        <T>
	 * @return
	 */
	public static <T> String sign(T obj, long maxAge) throws UnsupportedEncodingException, JsonProcessingException {
		JWTCreator.Builder builder = JWT.create();

		builder.withHeader(createHead())// header
				.withSubject(mapper.writeValueAsString(obj)); //装载一个对象信息

		if (maxAge >= 0) {
			long expMillis = System.currentTimeMillis() + maxAge;
			Date exp = new Date(expMillis);
			builder.withExpiresAt(exp);
		}

		return builder.sign(Algorithm.HMAC256(SECRET));
	}

	/**
	 * 解密
	 * 
	 * @param token  token字符串
	 * @param classT 解密后的类型
	 * @param        <T>
	 * @return
	 */
	public static <T> T unsign(String token, Class<T> classT) throws Exception {
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = verifier.verify(token);

		Date exp = jwt.getExpiresAt();
		if (exp != null && exp.after(new Date())) {
			String subject = jwt.getSubject();
			return mapper.readValue(subject, classT);
		}

		return null;
	}

}
