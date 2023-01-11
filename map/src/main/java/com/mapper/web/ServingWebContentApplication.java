package com.mapper.web;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@EnableAutoConfiguration
public class ServingWebContentApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ServingWebContentApplication.class);

		app.run(args);
	}
}
