package com.lancaiwu.alibaba.cloud.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;


/**
 * ClientDetailsServiceConfigurer：用来配置客户端详情服务，客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息。客户端就是指第三方应用
 * AuthorizationServerSecurityConfigurer：用来配置令牌端点(Token Endpoint)的安全约束.
 * AuthorizationServerEndpointsConfigurer：用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)。
 * EnableAuthorizationServer 注解表明是一个认证服务器
 *
 * @author lancaiwu
 * @since 2020/10/6 10:26
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    // 内存储存令牌
    private TokenStore tokenStore = new InMemoryTokenStore();

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端ID
                .withClient("zcs")
                .secret(new BCryptPasswordEncoder().encode("zcs"))
                // 权限范围
                .scopes("app")
                // 授权码模式
                .authorizedGrantTypes("authorization_code")
                // 随便写
                .redirectUris("www.lancaiwu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 开启 /oauth/token_key 验证端口无权访问
                .tokenKeyAccess("permitAll")
                // 开启 /oauth/check_token 验证端口认证权限访问
                .checkTokenAccess("isAuthenticated")
                .allowFormAuthenticationForClients();
    }


}
