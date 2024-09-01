package com.luyan.web;

import cn.hutool.core.io.FileUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

@SpringBootApplication(scanBasePackages = "com.luyan")
@MapperScan("com.luyan.mapper")
public class Main implements ApplicationRunner {
    @Value("${global.upload_covers}")
    private String uploadCovers;
    @Value("${global.upload_headers}")
    private String uploadHeaders;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Stream.of(uploadCovers, uploadHeaders).forEach((path) -> {
            if (!FileUtil.exist(path)) {
                FileUtil.mkdir(path);
            }
        });
    }
}