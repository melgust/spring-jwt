package com.example.demo;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.demo.utils.AppProperty;

@SpringBootApplication
@EnableConfigurationProperties({ AppProperty.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
    
    @PostConstruct
    public void init(){
      TimeZone.setDefault(TimeZone.getTimeZone("CST"));
    }

}
