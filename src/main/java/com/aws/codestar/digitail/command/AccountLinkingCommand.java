package com.aws.codestar.digitail.command;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.aws.codestar.digitail.bean.PageObjectBean;
import com.aws.codestar.digitail.bean.reply.FBReply;
import com.aws.codestar.digitail.bean.reply.ReplyObj;
import com.aws.codestar.digitail.util.BotUtil;
import com.aws.codestar.digitail.util.DigitailStringConstants;

@Component
public class AccountLinkingCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(AccountLinkingCommand.class);
	@Autowired
	BotUtil util;
	@Value("${linked}")
	private String linked;
	@Value("${unlinked}")
	private String unlinked;

	@Override
	public Serializable execute(PageObjectBean bean) {
		logger.info("Account is " + bean.getEntry()[0].getMessaging()[0].getAccount_linking().getStatus());
		ReplyObj reply = null;
		if (bean.getEntry()[0].getMessaging()[0].getAccount_linking().getStatus().equalsIgnoreCase(linked)) {

			// sending a default message
			reply = util.getReplyObject(DigitailStringConstants.DEFAULT);
			reply.getRecipient().setId(util.getSenderId(bean));
			reply.getMessage().setText("Hi " + util.getUserName(util.getSenderId(bean)) + "..! " + "The following orders are in progress and will be delivered in next 24 hours. ");

			try {
				util.postReply(util.getURI(), reply, FBReply.class);
			} catch (RestClientException | URISyntaxException e) {
				logger.error(e.getMessage());
			}

			String jsonPayload = util.getJsonPayload("order.json");
			logger.info(jsonPayload);
			reply = util.getReplyFromJson(jsonPayload);
			logger.info("reply :: " + reply);

			reply.getRecipient().setId(util.getSenderId(bean));
			reply.getMessage().getAttachment().getPayload().setRecipient_name(util.getUserName(util.getSenderId(bean)));
			reply.getMessage().getAttachment().getPayload().setTimestamp(20161208);
		} else if (bean.getEntry()[0].getMessaging()[0].getAccount_linking().getStatus().equalsIgnoreCase(unlinked)) {

		}
		return reply;
	}

}
