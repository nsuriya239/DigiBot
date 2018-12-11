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

@Component
public class GraceReplyPayloadCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(GraceReplyPayloadCommand.class);
	@Autowired
	BotUtil util;
	@Value("${graceReply}")
	private String graceReply;

	@Override
	public Serializable execute(PageObjectBean bean) {
		logger.info("Greeting payload command execute start");
		ReplyObj reply = util.getReplyObject("");		
		reply.getRecipient().setId(util.getSenderId(bean));
		reply.getMessage().setText(graceReply);
		return reply;
	}

}
