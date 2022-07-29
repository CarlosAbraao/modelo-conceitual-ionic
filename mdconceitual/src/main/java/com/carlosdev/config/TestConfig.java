package com.carlosdev.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.carlosdev.service.DBService;
import com.carlosdev.service.EmailService;
import com.carlosdev.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		dbService.instatiateTestDataBase();
		
		
		return true;
	}
	
	//INSTANCIA DO MOCK ATRAVES DO BEAN
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}

}
