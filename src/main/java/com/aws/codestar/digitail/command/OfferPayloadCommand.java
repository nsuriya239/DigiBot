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
public class OfferPayloadCommand implements PayloadCommand {
	
	Logger logger = LoggerFactory.getLogger(SupportPayloadCommand.class);
	@Autowired
	BotUtil util;
	@Value("${quick_reply_1}")
	private String quick_reply_1;

	public Serializable execute(PageObjectBean bean) {		
		ReplyObj reply = util.getReplyObject(DigitailStringConstants.IS_QUICK_REPLY, 2);
		reply.getMessage().setText(quick_reply_1);
		
		reply.getMessage().getQuick_replies()[0].setContent_type(DigitailStringConstants.TEXT);
		reply.getMessage().getQuick_replies()[0].setTitle(DigitailStringConstants.LAPTOPS);
		reply.getMessage().getQuick_replies()[0].setPayload(DigitailStringConstants.LAPTOPS);
		
		reply.getMessage().getQuick_replies()[1].setContent_type(DigitailStringConstants.TEXT);
		reply.getMessage().getQuick_replies()[1].setTitle(DigitailStringConstants.MOBILES);
		reply.getMessage().getQuick_replies()[1].setPayload(DigitailStringConstants.MOBILES);
		reply.getRecipient().setId(util.getSenderId(bean));
		return reply;
	}

}
