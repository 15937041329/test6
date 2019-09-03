package com.test.demo.publicres.config;

import com.test.demo.publicres.interceptor.UserTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: Test
 * @description: Api拦截配置
 * @author: Ban shifeng
 * @create: 2019-08-07 15:57
 **/
//@Configuration
public class MyWebAppConfig extends WebMvcConfigurationSupport {
    @Bean
    UserTokenInterceptor localInterceptor() {
        return new UserTokenInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/login")//登录接口放行
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");//swagger页面放行
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
