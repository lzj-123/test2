package com.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.sc.mapper") //扫描mapper接口的包
public class SbmApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SbmApplication.class, args);
	}

}
