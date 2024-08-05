package com.dws.TradeAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeAssignmentApplication.class, args);
	}

}
