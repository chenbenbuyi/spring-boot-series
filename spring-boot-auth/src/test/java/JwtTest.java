import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date 2022/6/19 17:43
 * @Created by csxian
 */
public class JwtTest {

    @Test
    public void createToken(){
        Map<String, Object> header = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND, 300);
        String token = JWT.create()
                // header 部分
                .withHeader(header)
                // payload部分
                .withClaim("name", "xiaoyuan")
                .withClaim("age", 23)
                .withExpiresAt(instance.getTime()) // 过期时间指定
                // signature  密钥保存在服务端
                .sign(Algorithm.HMAC256("123444"));

        System.out.println(token);
    }


    @Test
    public void verifyToken(){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("123444")).build();
        DecodedJWT verify = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bSI6IjE4NTgxMjkyOTEwQDE2My5jb20iLCJleHAiOjE2NTU2ODg3NDV9.Jgj9D5HS997atFLRSZmeWwWZpVtfRu01eIYq7gF2HrU");
        String name = verify.getClaim("name").asString();
        System.out.println(name);
    }

}
