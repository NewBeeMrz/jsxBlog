package com.ninth.jsx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ninth.jsx.dao","com.ninth.jsx.service.Impl","com.ninth.jsx.controller"})
@MapperScan("com.ninth.jsx.dao")
public class JsxApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsxApplication.class, args);
    }

}
