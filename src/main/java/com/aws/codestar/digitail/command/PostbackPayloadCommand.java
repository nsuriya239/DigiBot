package com.aws.codestar.digitail.command;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aws.codestar.digitail.bean.PageObjectBean;
import com.aws.codestar.digitail.bean.reply.ReplyObj;
import com.aws.codestar.digitail.util.BotUtil;
import com.aws.codestar.digitail.util.DigitailStringConstants;

@Component
public class PostbackPayloadCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(SupportPayloadCommand.class);
	@Autowired
	BotUtil util;

	@Override
	public Serializable execute(PageObjectBean bean) {
		ReplyObj reply = util.getReplyObject(DigitailStringConstants.IS_POSTBACK);
		String postbackPayload = util.getPostback(bean);
		String message = "We received your postback request, " + postbackPayload + ". Will get back at the earliest..";
		reply.getMessage().setText(message);
		reply.getRecipient().setId(util.getSenderId(bean));
		return reply;
	}

}
