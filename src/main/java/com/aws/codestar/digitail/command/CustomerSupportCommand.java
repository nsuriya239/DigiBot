package com.aws.codestar.digitail.command;

import java.io.Serializable;
import java.util.Map;

import org.crsh.console.jline.internal.Log;
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
public class CustomerSupportCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(SearchPayloadCommand.class);
	@Autowired
	BotUtil util;
	@Value("#{${Support}}")
	private Map<String, String> support;

	@Override
	public Serializable execute(PageObjectBean bean) {
		String message = util.getMessagePayload(bean);
		ReplyObj reply = null;
		Log.debug("support map :: " + support);
		logger.info("handling message :: CustomerSupportCommand :: " + message);

		String jsonPayload = util.getJsonPayload(support.get(DigitailStringConstants.SUPPORT));
		logger.debug(jsonPayload);
		reply = util.getReplyFromJson(jsonPayload);
		reply.getRecipient().setId(util.getSenderId(bean));

		return reply;
	}

}
