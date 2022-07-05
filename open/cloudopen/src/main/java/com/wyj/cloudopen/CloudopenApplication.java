package com.wyj.cloudopen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.wyj.cloudopen.mapper"})
public class CloudopenApplication {
	public static final String ADDITIONAL_LOCATION = "file:./";

	public static void main(String[] args) {
		System.setProperty("spring.config.additional-location",ADDITIONAL_LOCATION);
		SpringApplication.run(CloudopenApplication.class, args);
	}

}
