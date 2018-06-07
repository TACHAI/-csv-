package com.xmduruo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.xmduruo.dao","com.xmduruo.service","com.xmduruo.service.serviceImpl","com.xmduruo.controller"})
public class ModifycsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModifycsvApplication.class, args);
	}
}
