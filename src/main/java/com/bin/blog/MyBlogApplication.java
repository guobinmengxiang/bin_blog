package com.bin.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
//其注册到 Spring 的 IOC 容器中以供其他类调用
//添加 @MapperScan 注解将相应包下的所有 Mapper 接口扫描到容器中
@MapperScan("com.bin.blog.dao")
@SpringBootApplication
@ServletComponentScan
public class MyBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBlogApplication.class, args);
    }
}
