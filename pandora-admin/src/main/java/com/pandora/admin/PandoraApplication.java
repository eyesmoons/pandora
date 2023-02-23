package com.pandora.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.pandora.*")
@MapperScan({"com.pandora.dao.*.mapper"})
public class PandoraApplication {

    public static void main(String[] args) {
        SpringApplication.run(PandoraApplication.class, args);
        System.out.println("启动成功");
    }
}
