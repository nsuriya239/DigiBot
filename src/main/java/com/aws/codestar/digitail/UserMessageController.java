package com.aws.codestar.digitail;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import com.aws.codestar.digitail.bean.PageObjectBean;
import com.aws.codestar.digitail.bean.reply.FBReply;
import com.aws.codestar.digitail.bean.reply.ReplyObj;
import com.aws.codestar.digitail.command.AccountLinkingCommand;
import com.aws.codestar.digitail.command.AttachmentPayloadCommand;
import com.aws.codestar.digitail.command.BrowsePayloadCommand;
import com.aws.codestar.digitail.command.CustomerSupportCommand;
import com.aws.codestar.digitail.command.DefaultPayloadCommand;
import com.aws.codestar.digitail.command.GetStartedPayloadCommand;
import com.aws.codestar.digitail.command.GraceReplyPayloadCommand;
import com.aws.codestar.digitail.command.GreetingPayloadCommand;
import com.aws.codestar.digitail.command.OfferPayloadCommand;
import com.aws.codestar.digitail.command.PayloadCommand;
import com.aws.codestar.digitail.command.PostbackPayloadCommand;
import com.aws.codestar.digitail.command.SearchPayloadCommand;
import com.aws.codestar.digitail.command.SupportPayloadCommand;
import com.aws.codestar.digitail.command.SwearPayloadCommand;
import com.aws.codestar.digitail.util.BotUtil;
import com.aws.codestar.digitail.util.DigitailStringConstants;
import com.google.gson.Gson;

@Component
public class UserMessageController {

	Logger logger = LoggerFactory.getLogger(UserMessageController.class);
	@Value("#{'${greeting}'.split(',')}")
	private List<String> greetings;
	@Value("${help}")
	private String support;
	@Value("${helpMessage}")
	private String helpDesc;
	@Value("#{'${swearWords}'.split(',')}")
	private List<String> swearList;
	@Value("#{'${quickReplyPayloads}'.split(',')}")
	private List<String> quickReplyPayloadList;
	@Value("#{'${graceWords}'.split(',')}")
	private List<String> graceList;
	@Value("#{'${searchKeyList}'.split(',')}")
	private List<String> searchKeyList;
	@Value("#{${browseMap}}")
	private Map<String, String> browseMap;
	Map<String, PayloadCommand> command = new HashMap<String, PayloadCommand>();
	@Autowired
	GreetingPayloadCommand greetingCommand;
	@Autowired
	SwearPayloadCommand swearCommand;
	@Autowired
	AttachmentPayloadCommand attachmentCommand;
	@Autowired
	PostbackPayloadCommand postbackCommand;
	@Autowired
	SupportPayloadCommand supportCommand;
	@Autowired
	DefaultPayloadCommand defaultCommand;
	@Autowired
	AccountLinkingCommand accountLinkingCommand;
	@Autowired
	GetStartedPayloadCommand getStartedCommand;
	@Autowired
	OfferPayloadCommand offerCommand;
	@Autowired
	QuickReplyPayloadCommand quickreplyPayloadCommand;
	@Autowired
	GraceReplyPayloadCommand graceCommand;
	@Autowired
	SearchPayloadCommand searchCommand;
	@Autowired
	BrowsePayloadCommand browseCommand;
	@Autowired
	CustomerSupportCommand customerSupportCommand;
	@Autowired
	BotUtil util;

	@PostConstruct
	private void initializeCommandMap() {
		command.put(DigitailStringConstants.IS_GREETING, greetingCommand);
		command.put(DigitailStringConstants.IS_SWEAR, swearCommand);
		command.put(DigitailStringConstants.IS_ATTACHMENT, attachmentCommand);
		command.put(DigitailStringConstants.IS_POSTBACK, postbackCommand);
		command.put(DigitailStringConstants.IS_SUPPORT, supportCommand);
		command.put(DigitailStringConstants.IS_ACCOUNT_LINKING, accountLinkingCommand);
		command.put(DigitailStringConstants.GET_STARTED_PAYLOAD, getStartedCommand);
		command.put(DigitailStringConstants.OFFER, offerCommand);
		command.put(DigitailStringConstants.QUICK_REPLY_PAYLOAD, quickreplyPayloadCommand);
		command.put(DigitailStringConstants.IS_GRACEFUL, graceCommand);
		command.put(DigitailStringConstants.PRODUCT_SEARCH, searchCommand);
		command.put(DigitailStringConstants.IS_BROWSE, browseCommand);
		command.put(DigitailStringConstants.IS_CUSTOMER_SUPPORT, customerSupportCommand);
		command.put(DigitailStringConstants.IS_MESSAGE, defaultCommand);
	}

	public void sendReply(String payload) {
		logger.info("SendReply: start");
		ReplyObj reply = buildResponse(payload);

		try {
			if (reply != null) {
				FBReply fbReply = util.postReply(util.getURI(), reply, FBReply.class);
				logger.info(fbReply.toString());
			} else {
				logger.warn("reply is null");
			}
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
		} catch (RestClientException e) {
			if (e instanceof HttpStatusCodeException) {
				logger.info("HttpStatusCodeException");
				logger.error(((HttpStatusCodeException) e).getResponseBodyAsString());
			}
			logger.error(e.getMessage());
			logger.error("root cause", e.getRootCause());
			logger.error("cause", e.getCause());
		}
		logger.info("SendReply: End");
	}

	private ReplyObj buildResponse(String payload) {
		ReplyObj reply = null;
		String handler = DigitailStringConstants.DEFAULT;
		String message = "";
		Gson gson = new Gson();

		PageObjectBean bean = gson.fromJson(payload, PageObjectBean.class);

		if (bean.getEntry()[0].getMessaging()[0].getMessage() != null) {
			if (bean.getEntry()[0].getMessaging()[0].getMessage().getText() != null) {
				message = bean.getEntry()[0].getMessaging()[0].getMessage().getText();
				handler = DigitailStringConstants.IS_MESSAGE;
			} else {
				handler = DigitailStringConstants.IS_ATTACHMENT;
			}
		} else if (bean.getEntry()[0].getMessaging()[0].getPostback() != null) {
			handler = DigitailStringConstants.IS_POSTBACK;
			message = bean.getEntry()[0].getMessaging()[0].getPostback().getPayload();
		} else if (bean.getEntry()[0].getMessaging()[0].getAccount_linking() != null) {
			handler = DigitailStringConstants.IS_ACCOUNT_LINKING;
		}

		logger.info("handler :: " + handler);
		logger.info("message :: " + message);

		if (handler.equalsIgnoreCase(DigitailStringConstants.IS_MESSAGE)) {
			for (String greeting : greetings) {
				if (greeting.equalsIgnoreCase(message.trim())) {
					handler = DigitailStringConstants.IS_GREETING;
					break;
				}
			}

			for (String swear : swearList) {
				if (swear.equalsIgnoreCase(message.trim())) {
					handler = DigitailStringConstants.IS_SWEAR;
					break;
				}
			}

			if (support.equalsIgnoreCase(message.trim())) {
				handler = DigitailStringConstants.IS_SUPPORT;
			}

			for (String quickReply : quickReplyPayloadList) {
				logger.info("quickreply payload :: " + quickReply);
				if (quickReply.equals(message.trim())) {
					handler = DigitailStringConstants.QUICK_REPLY_PAYLOAD;
					break;
				}
			}

			for (String grace : graceList) {
				if (grace.equalsIgnoreCase(message.trim())) {
					handler = DigitailStringConstants.IS_GRACEFUL;
					break;
				}
			}

			if (bean.getEntry()[0].getMessaging()[0].getMessage().getQuick_reply() != null
					&& bean.getEntry()[0].getMessaging()[0].getMessage().getQuick_reply().getPayload() != null) {
				message = bean.getEntry()[0].getMessaging()[0].getMessage().getQuick_reply().getPayload();
				logger.info("handling quick reply payload in message for browse :: " + message);
				for (String searchKey : searchKeyList) {
					if (searchKey.equalsIgnoreCase(message.trim())) {
						handler = DigitailStringConstants.IS_BROWSE;
					}
				}
			}

			if (message.trim().equalsIgnoreCase(DigitailStringConstants.OFFER)) {
				handler = DigitailStringConstants.OFFER;
			}

			if (message.trim().equalsIgnoreCase(DigitailStringConstants.Customer_Support)
					|| message.trim().equalsIgnoreCase(DigitailStringConstants.SUPPORT)) {
				handler = DigitailStringConstants.IS_CUSTOMER_SUPPORT;
			}

			if (message.trim().equalsIgnoreCase(DigitailStringConstants.PRODUCT_SEARCH)
					|| message.trim().equalsIgnoreCase(DigitailStringConstants.Browse_Products)) {
				handler = DigitailStringConstants.PRODUCT_SEARCH;
			}
		}

		if (handler.equalsIgnoreCase(DigitailStringConstants.IS_POSTBACK)) {
			if (message.trim().equalsIgnoreCase(DigitailStringConstants.GET_STARTED_PAYLOAD)) {
				handler = DigitailStringConstants.GET_STARTED_PAYLOAD;
			} else if (message.trim().equalsIgnoreCase(DigitailStringConstants.OFFER)) {
				handler = DigitailStringConstants.OFFER;
			} else if (message.trim().equalsIgnoreCase(DigitailStringConstants.LAPTOPS1)) {
				handler = DigitailStringConstants.QUICK_REPLY_PAYLOAD;
			} else if (message.trim().equalsIgnoreCase(DigitailStringConstants.LAPTOPS)) {
				handler = DigitailStringConstants.QUICK_REPLY_PAYLOAD;
			} else if (message.trim().equalsIgnoreCase(DigitailStringConstants.MOBILES)) {
				handler = DigitailStringConstants.QUICK_REPLY_PAYLOAD;
			} else if (message.trim().equalsIgnoreCase(DigitailStringConstants.MOBILES1)) {
				handler = DigitailStringConstants.QUICK_REPLY_PAYLOAD;
			} else if (message.trim().equalsIgnoreCase(support)) {
				handler = DigitailStringConstants.IS_SUPPORT;
			} else if (message.trim().equalsIgnoreCase(DigitailStringConstants.PRODUCT_SEARCH)) {
				handler = DigitailStringConstants.PRODUCT_SEARCH;
			} else if (browseMap.containsKey(message)) {
				handler = DigitailStringConstants.IS_BROWSE;
			} else if (DigitailStringConstants.SUPPORT.equalsIgnoreCase(message)) {
				handler = DigitailStringConstants.IS_CUSTOMER_SUPPORT;
			}
		}

		logger.info("handler :: " + handler);

		if (handler != null) {
			reply = (ReplyObj) command.get(handler).execute(bean);
		}
		if (reply != null)
			logger.info("Reply payload :: " + gson.toJson(reply, ReplyObj.class));
		return reply;
	}

}
