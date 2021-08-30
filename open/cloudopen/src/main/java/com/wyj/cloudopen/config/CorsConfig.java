package com.wyj.cloudopen.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {

//    public void addCorsConfig(CorsRegistry corsRegistry){
//        corsRegistry.addMapping("**/")
//                .allowedOrigins("*")
//                .allowCredentials(true)
//                .allowedMethods("POST","GET")
//                .maxAge(3600);
//
//    }
}
