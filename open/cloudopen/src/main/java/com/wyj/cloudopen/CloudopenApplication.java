package com.wyj.cloudopen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.wyj.cloudopen.mapper"})
public class CloudopenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudopenApplication.class, args);
	}

}
