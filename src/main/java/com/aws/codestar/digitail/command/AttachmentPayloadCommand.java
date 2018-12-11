package com.digitail.command;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digitail.bean.PageObjectBean;
import com.digitail.bean.reply.ReplyObj;
import com.digitail.util.BotUtil;
import com.digitail.util.DigitailStringConstants;

@Component
public class AttachmentPayloadCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(SwearPayloadCommand.class);
	@Autowired
	BotUtil util;

	@Override
	public Serializable execute(PageObjectBean bean) {
		ReplyObj reply = util.getReplyObject(DigitailStringConstants.IS_ATTACHMENT);
		reply.getMessage().getAttachment().getPayload()
				.setUrl(bean.getEntry()[0].getMessaging()[0].getMessage().getAttachments()[0].getPayload().getUrl());
		reply.getMessage().getAttachment().setType(DigitailStringConstants.IMAGE);
		reply.getRecipient().setId(util.getSenderId(bean));
		return reply;
	}

}
