package com.xxl.sso.sample.config;

import com.xxl.sso.core.auth.reactive.XxlSsoNativeReactiveFilter;
import com.xxl.sso.core.bootstrap.XxlSsoBootstrap;
import com.xxl.sso.core.store.impl.RedisLoginStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuxueli 2026-08-07
 */
@Configuration
public class XxlSsoConfig {


    @Value("${xxl-sso.token.key}")
    private String tokenKey;

    @Value("${xxl-sso.token.timeout}")
    private long tokenTimeout;

    @Value("${xxl-sso.store.redis.nodes}")
    private String redisNodes;

    @Value("${xxl-sso.store.redis.user}")
    private String redisUser;

    @Value("${xxl-sso.store.redis.password}")
    private String redisPassword;

    @Value("${xxl-sso.store.redis.keyprefix}")
    private String redisKeyprefix;

    @Value("${xxl-sso.client.excluded.paths}")
    private String excludedPaths;


    /**
     * 1、配置 XxlSsoBootstrap
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public XxlSsoBootstrap xxlSsoBootstrap() {

        XxlSsoBootstrap bootstrap = new XxlSsoBootstrap();
        bootstrap.setLoginStore(new RedisLoginStore(
                redisNodes,
                redisUser,
                redisPassword,
                redisKeyprefix));
        bootstrap.setTokenKey(tokenKey);
        bootstrap.setTokenTimeout(tokenTimeout);
        return bootstrap;
    }


    /**
     * 2、配置 XxlSso WebFlux Filter
     *
     * WebFlux 中声明为 Spring Bean 的 WebFilter 将自动生效，无需 FilterRegistrationBean；
     *
     * @return
     */
    @Bean
    public XxlSsoNativeReactiveFilter xxlSsoNativeFilter() {

        // 2.1、build xxl-sso webflux filter
        return new XxlSsoNativeReactiveFilter(excludedPaths);
    }


}
