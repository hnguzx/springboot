import com.guzx.chapter2.config.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

public class RedisTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        RedisTemplate<Object, Object> template = applicationContext.getBean(RedisTemplate.class);
        template.opsForValue().set("name4", "testName");
        template.opsForHash().put("hash", "field", "hvalue");
        new RedisTest().useRedisCallback(template);
        new RedisTest().useSessionCallback(template);
        new RedisTest().useSessionCallback2(template);
    }

    // 在一个连接中多次操作
    public void useRedisCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set("key1".getBytes(), "value1".getBytes());
                redisConnection.hSet("hash".getBytes(), "hkey".getBytes(), "hvalue".getBytes());
                return null;
            }
        });
    }

    // 在一个连接中多次操作，推荐使用
    public void useSessionCallback(RedisTemplate redisTemplate) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                redisOperations.opsForValue().set("key2", "value2");
                redisOperations.opsForHash().put("hash", "hkey2", "hvalue2");
                return null;
            }
        });
    }

    public void useSessionCallback2(RedisTemplate redisTemplate) {
        redisTemplate.execute((RedisConnection redisConnection) -> {
            redisConnection.set("key3".getBytes(), "value3".getBytes());
            redisConnection.hSet("hash".getBytes(), "hkey3".getBytes(), "hvalue3".getBytes());
            return null;
        });
    }
}
