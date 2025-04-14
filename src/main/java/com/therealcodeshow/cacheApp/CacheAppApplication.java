package com.therealcodeshow.cacheApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(CacheAppApplication.class, args);
	}
}
