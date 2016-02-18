package com.verisk.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.verisk")
@EnableAutoConfiguration
public class PublisherApplication extends ServletInitializer{

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(PublisherApplication.class, args);
		
		
		SqlServiceTest sqlServiceTest = context.getBean(SqlServiceTest.class);
		
		System.out.println(sqlServiceTest.getHelloMessage());
	}
	
	
}
