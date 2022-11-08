package com.lbozo.AcidRun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcidRunApplication {

	public static void main(String[] args) {
		System.out.println("heheheha");
		new Game();
		SpringApplication.run(AcidRunApplication.class, args);
	}

}
