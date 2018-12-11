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
public class SearchPayloadCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(SearchPayloadCommand.class);
	@Autowired
	BotUtil util;
	@Value("${quick_reply_browse}")
	private String quick_reply_title;

	@Override
	public Serializable execute(PageObjectBean bean) {		
		ReplyObj reply = util.getReplyObject(DigitailStringConstants.IS_QUICK_REPLY, 3);
		reply.getMessage().setText(quick_reply_title);
		
		reply.getMessage().getQuick_replies()[0].setContent_type(DigitailStringConstants.TEXT);
		reply.getMessage().getQuick_replies()[0].setTitle("Laptops");
		reply.getMessage().getQuick_replies()[0].setPayload(DigitailStringConstants.BROWSE_LAPTOP);
		
		reply.getMessage().getQuick_replies()[1].setContent_type(DigitailStringConstants.TEXT);
		reply.getMessage().getQuick_replies()[1].setTitle("Mobiles");
		reply.getMessage().getQuick_replies()[1].setPayload(DigitailStringConstants.BROWSE_MOBILE);
		
		reply.getMessage().getQuick_replies()[2].setContent_type(DigitailStringConstants.TEXT);
		reply.getMessage().getQuick_replies()[2].setTitle("TVs");
		reply.getMessage().getQuick_replies()[2].setPayload(DigitailStringConstants.BROWSE_TV);
		
		reply.getRecipient().setId(util.getSenderId(bean));
		return reply;
	}

}
