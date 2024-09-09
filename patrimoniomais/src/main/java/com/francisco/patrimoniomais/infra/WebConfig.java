package com.francisco.patrimoniomais.infra;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://10.0.2:8080", "http://localhost:8080")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
