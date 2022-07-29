package com.carlosdev.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

// IMPLEMENTAÇÃO DO EMAIL MOCADO

public class MockEmailService extends AbstractEmailService {
	
	// MOSTRAR O EMAIL NO LOG DO SERVIDOR
	private  static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	
	// MENSAGEM QUE SERÁ IMPRESSA NO SERVIDOR
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de email...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
		
	}
	
	

}
