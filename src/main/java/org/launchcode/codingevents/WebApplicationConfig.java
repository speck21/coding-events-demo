package org.launchcode.codingevents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {
    //create spring-managed object to allow the app to access our filter
    @Bean
    public AuthenticationFilter authenticationFilter(){
        return new AuthenticationFilter();
    }

    //register the filter with the spring container
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(authenticationFilter());
    }


}
