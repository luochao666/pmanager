package UserManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 
 * @author 超帅
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml" })
public class RedisTest
{
    @Autowired
    RedisTemplate<String, String> redisTemple;
    
    @Test
    public void test()
    {
        for (int i = 1; i < 1200; i++)
        {
        redisTemple.delete("test" + i);
        }
    }
    

}
