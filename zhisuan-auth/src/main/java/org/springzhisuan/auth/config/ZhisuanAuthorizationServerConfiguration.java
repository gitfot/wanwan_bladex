/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springzhisuan.auth.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springzhisuan.auth.constant.AuthConstant;
import org.springzhisuan.auth.granter.ZhisuanTokenGranter;
import org.springzhisuan.auth.service.ZhisuanClientDetailsServiceImpl;
import org.springzhisuan.core.redis.cache.ZhisuanRedis;
import org.springzhisuan.core.social.props.SocialProperties;
import org.springzhisuan.system.user.feign.IUserClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务器配置
 *
 * @author Chill
 */
@Order
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class ZhisuanAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	private final DataSource dataSource;

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailsService;

	private final TokenStore tokenStore;

	private final TokenEnhancer jwtTokenEnhancer;

	private final JwtAccessTokenConverter jwtAccessTokenConverter;

	private final ZhisuanRedis zhisuanRedis;

	private final IUserClient userClient;

	private final SocialProperties socialProperties;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		//获取自定义tokenGranter
		TokenGranter tokenGranter = ZhisuanTokenGranter.getTokenGranter(authenticationManager, endpoints, zhisuanRedis, userClient, socialProperties);

		//配置端点
		endpoints.tokenStore(tokenStore)
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.tokenGranter(tokenGranter);

		//扩展token返回结果
		if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
			TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
			List<TokenEnhancer> enhancerList = new ArrayList<>();
			enhancerList.add(jwtTokenEnhancer);
			enhancerList.add(jwtAccessTokenConverter);
			tokenEnhancerChain.setTokenEnhancers(enhancerList);
			//jwt增强
			endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
		}
	}

	/**
	 * 配置客户端信息
	 */
	@Override
	@SneakyThrows
	public void configure(ClientDetailsServiceConfigurer clients) {
		ZhisuanClientDetailsServiceImpl clientDetailsService = new ZhisuanClientDetailsServiceImpl(dataSource);
		clientDetailsService.setSelectClientDetailsSql(AuthConstant.DEFAULT_SELECT_STATEMENT);
		clientDetailsService.setFindClientDetailsSql(AuthConstant.DEFAULT_FIND_STATEMENT);
		clients.withClientDetails(clientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer
			.allowFormAuthenticationForClients()
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
}
