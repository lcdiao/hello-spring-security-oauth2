package cn.lcdiao.spring.security.oauth2.server.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

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

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
        return DataSourceBuilder.create().build();
    }

    @Bean
    public TokenStore tokenStore() {
        // 基于 JDBC 实现，令牌(token)保存到数据库
        return new JdbcTokenStore(dataSource());
    }

    @Bean
    public ClientDetailsService jdbcbClientDetailsService() {
        // 从数据库读取客户端配置，基于 JDBC 实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(dataSource());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients.withClientDetails(jdbcbClientDetailsService());
        /*clients
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
                .redirectUris("https://www.baidu.com");*/
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
