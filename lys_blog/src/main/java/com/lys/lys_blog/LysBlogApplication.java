package com.lys.lys_blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@MapperScan("com.lys.lys_blog.mapper")
@SpringBootApplication
public class LysBlogApplication{

    public static void main(String[] args) {
        SpringApplication.run(LysBlogApplication.class, args);
    }

}
