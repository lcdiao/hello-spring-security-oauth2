package cn.lcdiao.spring.security.oauth2.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author diao
 * @date 2019/10/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuth2ServerApplicationTests {

    @Test
    public void testPasswordEncoder() {
        System.out.println(new BCryptPasswordEncoder().encode("secret"));
    }
}
