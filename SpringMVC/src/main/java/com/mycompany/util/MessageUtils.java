package com.mycompany.util;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

public class MessageUtils{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);
	
	private MessageSource message;
	
	public MessageSource getMessage() {
		return message;
	}

	public void setMessage(MessageSource message) {
		this.message = message;
	}
	
	public String getMessage(String code) {
		logger.info("getMessage call");
		return message.getMessage(code, null, Locale.getDefault());
	}
	
}
