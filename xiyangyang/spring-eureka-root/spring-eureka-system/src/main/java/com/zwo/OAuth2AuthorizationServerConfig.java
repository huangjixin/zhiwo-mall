package com.zwo;

import java.util.Arrays;

//作者：林塬 链接：https:// www.jianshu.com/p/227f7e7503cb
//來源：简书 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.zwo.modules.system.vo.CustomTokenEnhancer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore tokenStore;

	// 告诉Spring Security Token的生成方式
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(tokenStore).accessTokenConverter(jwtAccessTokenConverter())
//				.authenticationManager(authenticationManager);
//	}
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    tokenEnhancerChain.setTokenEnhancers(
	      Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter()));
	 
	    endpoints.tokenStore(tokenStore())
	             .tokenEnhancer(tokenEnhancerChain)
	             .authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer
				// 允许所有资源服务器访问公钥端点（/oauth/token_key）
				// 只允许验证用户访问令牌解析端点（/oauth/check_token）
				.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
				// 允许客户端发送表单来进行权限认证来获取令牌
				.allowFormAuthenticationForClients();
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	// 使用私钥编码 JWT 中的 OAuth2 令牌
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("bhm.jks"),
				"bhm123".toCharArray());
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("bhm"));
		return converter;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() // 测试用，将客户端信息存储在内存中
				.withClient("client") // client_id
				.secret("secret") // client_secret
				.authorizedGrantTypes("authorization_code") // 该client允许的授权类型
				.scopes("app") // 允许的授权范围
				.autoApprove(true); // 登录后绕过批准询问(/oauth/confirm_access)

	}
	
	
	 
	@Bean
	public TokenEnhancer tokenEnhancer() {
	    return new CustomTokenEnhancer();
	}
}
