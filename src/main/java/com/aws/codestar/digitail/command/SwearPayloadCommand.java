package com.digitail.command;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digitail.bean.PageObjectBean;
import com.digitail.bean.reply.ReplyObj;
import com.digitail.util.BotUtil;
import com.digitail.util.DigitailStringConstants;

@Component
public class SwearPayloadCommand implements PayloadCommand {

	@Value("${swearReply}")
	private String swearReply;
	Logger logger = LoggerFactory.getLogger(SwearPayloadCommand.class);
	@Autowired
	BotUtil util;

	public Serializable execute(PageObjectBean bean) {
		logger.info("SwearPayloadCommand execute start");
		ReplyObj reply = util.getReplyObject(DigitailStringConstants.IS_SWEAR);
		String message = swearReply;
		reply.getMessage().setText(message);
		reply.getRecipient().setId(util.getSenderId(bean));
		return reply;
	}

}
