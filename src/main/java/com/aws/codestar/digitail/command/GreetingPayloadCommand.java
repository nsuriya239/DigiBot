package com.aws.codestar.digitail.command;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aws.codestar.digitail.bean.PageObjectBean;
import com.aws.codestar.digitail.bean.reply.ReplyObj;
import com.aws.codestar.digitail.util.BotUtil;

@Component
public class GreetingPayloadCommand implements PayloadCommand {

	@Autowired
	private BotUtil util;
	Logger logger = LoggerFactory.getLogger(GreetingPayloadCommand.class);

	public Serializable execute(PageObjectBean bean) {
		logger.info("Greeting payload command execute start");
		ReplyObj reply = util.getReplyObject("");
		String senderId = util.getSenderId(bean);
		String message = bean.getEntry()[0].getMessaging()[0].getMessage().getText();
		String userName = util.getUserName(senderId);
		message = message + " " + userName;
		reply.getMessage().setText(message);
		reply.getRecipient().setId(senderId);
		logger.info("Greeting payload command execute End");
		return reply;
	}

}
