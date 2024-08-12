package cnblogs.chenbenbuyi;

import cn.hutool.json.JSONUtil;
import cnblogs.chenbenbuyi.pojo.SerialUser;
import cnblogs.chenbenbuyi.pojo.User;
import cnblogs.chenbenbuyi.service.CacheService;
import cnblogs.chenbenbuyi.utils.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @date 2021/3/24 22:03
 * @Description
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    CacheService cacheService;

    @BeforeEach
    public void test() {
        // 获取连接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
    }

    /**
     * redisTemplate 本身包含了一些常用的操作
     * redisTemplate.opsXXX 获取操作相应数据类型的对象
     */
    @Test
    public void test1() {
        // 获取不同的数据类型对象 。。。
        ZSetOperations zset = redisTemplate.opsForZSet();
        zset.add("key", "value", 1);
        ListOperations list = redisTemplate.opsForList();
        list.rightPush("list", "value");
    }

    /**
     * 真实场景中一般以json的形式将对象存入Redis
     * 此示例中由于User没有实现序列化接口，无法完成序列化因而无法写入Redis
     * 异常：
     * SerializationException: Cannot serialize; nested exception is org.springframework.core.serializer.support.SerializationFailedException:
     * Failed to serialize object using DefaultSerializer
     */
    @Test
    public void wrongTest() {
        User user = new User(1L);
        redisTemplate.opsForValue().set("user", user);
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    /**
     * 真实场景中一般以json的形式将对象存入Redis
     * 要么实现序列化接口，默认的序列化器会进行序列化
     * 要么通过第三方类库序列化成 json 字符串
     */
    @Test
    public void rightTest() throws JsonProcessingException {
        User user = new User(1L);
//        String json = new ObjectMapper().writeValueAsString(user);
        String json = JSONUtil.toJsonStr(user);
        redisTemplate.opsForValue().set("user", json);
        System.out.println(redisTemplate.opsForValue().get("user"));

        /**
         *   注意：反序列化的时候，SerialUser 需要默认构造器
         */
        SerialUser serialUser = new SerialUser(2L);
        redisTemplate.opsForValue().set("serialUser", serialUser);
        System.out.println(redisTemplate.opsForValue().get("serialUser"));
    }


    @Test
    public void verifyTest() {
        redisTemplate.opsForValue().set("chen", "shaoxian");
        cacheService.getNameformDb();
        System.out.println(redisTemplate.opsForValue().get("chen"));
        System.out.println(redisTemplate.opsForValue().get("test::test"));
    }


    @Test
    public void testHash() {
        Map<Object, Object> chen = redisTemplate.opsForHash().entries("chen");
        System.out.println(chen);
//        redisTemplate.opsForHash().delete("chen", "k");
        System.out.println("================");
        Map<Object, Object> chen2 = redisTemplate.opsForHash().entries("chen");
        System.out.println(chen2);

        redisTemplate.opsForHash().increment("chen", "num", 20);

    }

    @Test
    public void hashKeys() {
        Set<Object> chen = redisTemplate.opsForHash().keys("chen");
        boolean b = redisTemplate.opsForHash().hasKey("chen", "1");
        System.out.println(chen);
    }

    @Test
    public void setRedisTemplateNu() throws InterruptedException {
        redisTemplate.opsForSet().remove("REPO_TASK", 1);
        int size = this.redisTemplate.opsForSet().members("REPO_TASK").size();
        while (size >= 5) {
            TimeUnit.SECONDS.sleep(30);
            size = this.redisTemplate.opsForSet().members("REPO_TASK").size();
        }
        System.out.println("结束");
    }

    @Test
    public  void testoo(){
        SerialUser serialUser = new SerialUser(2L);
        String json = "{\"copyright\":\"无\",\"vulSolution\":\"\",\"createType\":3,\"programmingLanguageVersion\":\"\",\"createdTime\":1706855248000,\"encryptions\":[{\"name\":\"无\",\"standard\":3,\"id\":2218}],\"id\":284179,\"programmingLanguageName\":\"JavaScript\",\"brief\":\"`AsyncIterator.prototype`, or a shared object to use.1\",\"publishTime\":1703952000000,\"updatedTime\":1715928404000,\"strId\":\"e83963fc-413c-4a2e-bc1f-2d444df815f3\",\"eccn\":\"\",\"externalId\":\"9993-3147-0001\",\"env\":[{\"urlType\":3,\"framework\":\"\",\"deleted\":0,\"frameworkName\":null,\"strId\":\"d010734c-7dba-4773-b9f6-25cc7d1f094a\",\"module\":\"\",\"envDesc\":\"\",\"compUrl\":\"git clone -b 1.0.0 ssh://zcode@10.41.103.195:29418/os-prelibrary/asynciterator.prototype\",\"downloadUrl\":{\"name\":\"asynciterator.prototype\",\"version\":\"1.0.0\"},\"formatType\":0,\"gav\":\"asynciterator.prototype:1.0.0\",\"status\":0},{\"urlType\":3,\"framework\":\"\",\"deleted\":0,\"frameworkName\":null,\"strId\":\"5cfe4ed0-4ae5-456b-bf15-b485a16f3eea\",\"module\":\"\",\"envDesc\":\"\",\"compUrl\":\"npm install asynciterator.prototype@1.0.0 --registry https://arttest.zte.com.cn/artifactory/api/npm/dev-release-npm-nj/\",\"downloadUrl\":{\"name\":\"asynciterator.prototype\",\"version\":\"1.0.0\"},\"formatType\":1,\"gav\":\"asynciterator.prototype:1.0.0\",\"status\":0}],\"vulSolutionTime\":\"\",\"component\":{\"brief\":\"`AsyncIterator.prototype`, or a shared object to use.\",\"devType\":1,\"projHomepage\":\"https://github.com/ljharb/AsyncIterator.prototype#readme\",\"country\":\"美国\",\"devNumber\":1,\"strId\":\"6de588de-e0da-46f5-a64b-d989627c2db8\",\"countryCode\":\"0222\",\"name\":\"asynciterator.prototype\",\"externalId\":\"9993-3147\",\"id\":48402,\"libraryType\":2,\"devName\":\"[ljharb]\"},\"licenses\":[{\"storetype\":\"[1, 2]\",\"disable\":false,\"name\":\"MIT License\",\"id\":267553,\"relationship\":2,\"licenseId\":122,\"family\":\"PERMISSIVE\",\"controlAttribute\":2,\"parentId\":267552}],\"deleted\":0,\"name\":\"1.0.0\",\"programmingLanguageId\":54,\"libraryType\":2,\"status\":0}";
        redisTemplate.opsForValue().set("chen",JSONUtil.toJsonStr(serialUser));
        redisTemplate.opsForValue().set("chen2",json);
        redisTemplate.opsForValue().set("chen3","tessssssssssssssss");
    }
}
