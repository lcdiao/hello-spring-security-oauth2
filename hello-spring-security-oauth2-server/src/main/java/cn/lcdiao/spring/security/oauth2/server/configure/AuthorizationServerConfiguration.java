package cn.lcdiao.spring.security.oauth2.server.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author diao
 * @date 2019/10/12
 * 配置认证服务器
 */
@Configuration
//开启授权服务
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients
                // 使用内存设置
                .inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret(passwordEncoder.encode("secret"))
                // 授权类型（授权码模式）
                .authorizedGrantTypes("authorization_code")
                // 授权范围
                .scopes("app")
                // 注册回调地址
                .redirectUris("https://www.baidu.com");
    }
    /*
    选择授权后会跳转到百度，浏览器地址上还会包含一个授权码（code=1JuO6V），浏览器地址栏会显示如下地址：
    https://www.baidu.com/?code=1JuO6V
    向服务器申请令牌
    通过 CURL 或是 Postman 请求
    curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=1JuO6V' "http://client:secret@localhost:8080/oauth/token"

     返回如下
     {
    "access_token": "f3820848-da32-4daf-97bb-f736d4bb8024",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "app"
}
     */
}
