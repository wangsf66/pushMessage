package com.pushMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.douglei.orm.spring.boot.starter.TransactionComponentScan;



@SpringBootApplication
@TransactionComponentScan(packages = { "com.ibs","com.pushMessage","com.smartone"})
@ComponentScan(basePackages= {"com.ibs","com.pushMessage","com.smartone"})
public class PushMessageApp{
	public static void main(String[] args) {
		SpringApplication.run(PushMessageApp.class, args);
	}
}

