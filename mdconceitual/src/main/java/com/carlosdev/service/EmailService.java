package com.carlosdev.service;

import org.springframework.mail.SimpleMailMessage;

import com.carlosdev.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
