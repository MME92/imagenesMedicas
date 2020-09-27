package com.imagenesMedicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class ImagenesMedicasApplication extends SpringBootServletInitializer {

    private static Class<ImagenesMedicasApplication> application = ImagenesMedicasApplication.class;

    public static void main(String[] args) {
        SpringApplication.run(application, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(application);
    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/","classpath:/other-resources/");
    }

}
