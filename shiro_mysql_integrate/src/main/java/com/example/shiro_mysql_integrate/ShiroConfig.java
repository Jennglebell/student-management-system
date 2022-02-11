package com.example.shiro_mysql_integrate;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        // Spring会自动new出来这个securityManager

        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);

        HashMap<String, String> filterChain = new HashMap<>();
        filterChain.put("/index", "anon");
        //首页就是anonymous filter，因为对于没有登陆的用户，我们可能会展示首页的一点点内容，对登陆的用户展示更多的内容
        filterChain.put("/**", "authcBasic"); //对其他任何地址,都需要basic authentication
        factoryBean.setFilterChainDefinitionMap(filterChain);

        return factoryBean;
    }
}
