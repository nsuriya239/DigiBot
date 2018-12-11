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
public class GetStartedPayloadCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(GetStartedPayloadCommand.class);
	@Autowired
	BotUtil util;
	@Value("${GET_STARTED_REPLY}")
	private String getStartedReply;

	@Override
	public Serializable execute(PageObjectBean bean) {
		String senderId = util.getSenderId(bean);
		String userName = util.getUserName(senderId);
		String message = "Hey " + userName + ".. " + getStartedReply;

		ReplyObj reply = util.getReplyObject(DigitailStringConstants.IS_ATTACHMENT, 3);
		reply.getMessage().getAttachment().getPayload().setTemplate_type(DigitailStringConstants.TEMPLATE_TYPE);
		reply.getMessage().getAttachment().getPayload().setText(message);

		reply.getMessage().getAttachment().getPayload().getButtons()[0].setType(DigitailStringConstants.POSTBACK);
		reply.getMessage().getAttachment().getPayload().getButtons()[0].setPayload(DigitailStringConstants.OFFER);
		reply.getMessage().getAttachment().getPayload().getButtons()[0].setTitle(DigitailStringConstants.OFFER);

		reply.getMessage().getAttachment().getPayload().getButtons()[1].setType(DigitailStringConstants.POSTBACK);
		reply.getMessage().getAttachment().getPayload().getButtons()[1].setPayload(DigitailStringConstants.SUPPORT);
		reply.getMessage().getAttachment().getPayload().getButtons()[1].setTitle(DigitailStringConstants.SUPPORT);

		reply.getMessage().getAttachment().getPayload().getButtons()[2].setType(DigitailStringConstants.POSTBACK);
		reply.getMessage().getAttachment().getPayload().getButtons()[2]
				.setPayload(DigitailStringConstants.PRODUCT_SEARCH);
		reply.getMessage().getAttachment().getPayload().getButtons()[2]
				.setTitle(DigitailStringConstants.PRODUCT_SEARCH);

		reply.getMessage().getAttachment().setType(DigitailStringConstants.TEMPLATE);
		reply.getRecipient().setId(senderId);
		return reply;
	}

}
