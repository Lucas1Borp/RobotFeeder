package com.inmetrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.inmetrics.dao.MongoDAO;

@SpringBootApplication
public class RoboApplication extends MongoDAO {

	public static void main(String[] args) {
		SpringApplication.run(RoboApplication.class, args);
		
	}

}
