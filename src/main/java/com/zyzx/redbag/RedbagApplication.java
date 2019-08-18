package com.zyzx.redbag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zyzx.redbag.mapper")
public class RedbagApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedbagApplication.class, args);
	}

}
