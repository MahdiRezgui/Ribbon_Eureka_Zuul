package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



@RestController
@EnableDiscoveryClient
@SpringBootApplication
public class GreetingServerApplication {
	@Value("${server.port}")
	String port;
	private static Logger log = LoggerFactory.getLogger(GreetingServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GreetingServerApplication.class, args);
	}
	
	@HystrixCommand(fallbackMethod="buildFallBackUser")
	@RequestMapping(value = "/hi")
	public String home() {
		return "Hi! im connected to the port "+port;
	}
	
	private void buildFallBackUser() {
		log.debug("Waiting too long");
	}
	
}
