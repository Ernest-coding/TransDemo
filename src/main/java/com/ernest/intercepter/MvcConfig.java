package com.ernest.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器基本配置
 *
 * @author ernest
 * @date 2022年03月22
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 LoginHandlerInterceptor 拦截器
        /*InterceptorRegistration loginInter = registry.addInterceptor(new LoginHandlerInterceptor());
        // 拦截所有路径
        loginInter.addPathPatterns("/user/**", "/coy/**", "/");
        loginInter.excludePathPatterns(
                "/files/**",
                "*.js", "*.css", "*.jpg", "*.png", "*.gif", "*.woff*"
        );   // 添加不拦截路径

        // 注册 AuthorityIntercept 拦截器
        InterceptorRegistration authorityInter = registry.addInterceptor(new AuthorityInterceptor());
        // 拦截所有路径
        authorityInter.addPathPatterns("/user/**", "/coy/**", "/");
        authorityInter.excludePathPatterns(
                "/files/**",
                "*.js", "*.css", "*.jpg", "*.png", "*.gif", "*.woff*"
        );   // 添加不拦截路径*/
//        authorityInter.addPathPatterns("/user/**", "/man/**", "/sign/**");


        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
