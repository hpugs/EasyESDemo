package com.hpugs.easyes;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EsMapperScan("com.hpugs.easyes.mapper")
@SpringBootApplication
public class EasyEsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyEsDemoApplication.class, args);
    }

}
