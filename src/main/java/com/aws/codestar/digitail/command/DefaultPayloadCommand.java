package com.aws.codestar.digitail.command;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aws.codestar.digitail.bean.PageObjectBean;
import com.aws.codestar.digitail.bean.reply.ReplyObj;
import com.aws.codestar.digitail.util.BotUtil;
import com.aws.codestar.digitail.util.DigitailStringConstants;

@Component
public class DefaultPayloadCommand implements PayloadCommand {

	@Autowired
	BotUtil util;
	Logger logger = LoggerFactory.getLogger(DefaultPayloadCommand.class);
	@Value("${defaultMessage}")
	String defaultMessage;

	@Override
	public Serializable execute(PageObjectBean bean) {
		ReplyObj reply = util.getReplyObject(DigitailStringConstants.DEFAULT);
		reply.getRecipient().setId(util.getSenderId(bean));
		// reply.getMessage().setText(util.getUserMessage(bean));
		reply.getMessage().setText(defaultMessage);
		return reply;
	}

}
