package com.digitail.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.digitail.bean.Attachment;
import com.digitail.bean.Button;
import com.digitail.bean.Element;
import com.digitail.bean.PageObjectBean;
import com.digitail.bean.Payload;
import com.digitail.bean.UserProfile;
import com.digitail.bean.reply.QuickReply;
import com.digitail.bean.reply.Recipient;
import com.digitail.bean.reply.ReplyMessage;
import com.digitail.bean.reply.ReplyObj;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BotUtil {

	Logger logger = LoggerFactory.getLogger(BotUtil.class);
	@Value("${ACCESS_TOKEN}")
	private String access_token;
	@Value("${GRAPH_URL}")
	private String graph_url;
	@Value("${jsonPath}")
	private String jsonPath;
	@Value("${MESSENGER_URL}")
	private String messenger_url;

	public String getUserName(String senderId) {
		String name = "";
		RestTemplate template = new RestTemplate();
		try {
			UserProfile profile = template.getForObject(graph_url + senderId + "?access_token=" + access_token,
					UserProfile.class);
			logger.debug(profile.getFirst_name());
			return profile.getFirst_name();
		} catch (RestClientException ex) {
			logger.error(ex.getMessage());
		}
		return name;
	}

	public String getSenderId(PageObjectBean bean) {
		if (bean != null)
			return bean.getEntry()[0].getMessaging()[0].getSender().getId();
		return "";
	}

	public ReplyObj getReplyObject(String type) {
		ReplyObj reply = new ReplyObj();
		Recipient recipient = new Recipient();
		ReplyMessage replyMessage = new ReplyMessage();
		reply.setRecipient(recipient);
		reply.setMessage(replyMessage);
		if (type.equalsIgnoreCase(DigitailStringConstants.IS_ATTACHMENT)) {
			reply.setMessage(replyMessage);
			Attachment attach = new Attachment();
			Payload payld = new Payload();
			attach.setPayload(payld);
			replyMessage.setAttachment(attach);
		}
		return reply;
	}

	public ReplyObj getReplyObject(String type, int buttonCount) {
		ReplyObj reply = new ReplyObj();
		Recipient recipient = new Recipient();
		ReplyMessage replyMessage = new ReplyMessage();
		reply.setRecipient(recipient);
		reply.setMessage(replyMessage);
		if (type.equalsIgnoreCase(DigitailStringConstants.IS_ATTACHMENT)) {
			Attachment attach = new Attachment();
			Payload payld = new Payload();
			Button[] buttons = new Button[buttonCount];
			for (int i = 0; i < buttonCount; i++) {
				Button button = new Button();
				buttons[i] = button;
			}
			logger.info("button array :: " + buttons.length);
			payld.setButtons(buttons);
			attach.setPayload(payld);
			replyMessage.setAttachment(attach);
		} else if (type.equalsIgnoreCase(DigitailStringConstants.IS_QUICK_REPLY)) {
			QuickReply[] quicks = new QuickReply[buttonCount];
			for (int i = 0; i < buttonCount; i++) {
				QuickReply quick = new QuickReply();
				quicks[i] = quick;
			}
			replyMessage.setQuick_replies(quicks);
		} else if (type.equalsIgnoreCase(DigitailStringConstants.IS_GENERIC_ELEMENT)) {
			Attachment attach = new Attachment();
			Payload payld = new Payload();
			Element[] elements = new Element[buttonCount];
			for (int i = 0; i < buttonCount; i++) {
				Element element = new Element();
				Button[] buttons = new Button[2];
				for (int j = 0; j < 2; j++) {
					Button button = new Button();
					buttons[j] = button;
				}
				element.setButtons(buttons);
				elements[i] = element;
			}
			payld.setElements(elements);
			attach.setPayload(payld);
			replyMessage.setAttachment(attach);
		}
		return reply;
	}

	public String getPostback(PageObjectBean bean) {
		String postbackPayload = "";
		if (bean != null) {
			postbackPayload = bean.getEntry()[0].getMessaging()[0].getPostback().getPayload();
		}
		return postbackPayload;
	}

	public String getUserMessage(PageObjectBean bean) {
		String message = "";
		if (bean.getEntry()[0].getMessaging()[0].getMessage() != null) {
			if (bean.getEntry()[0].getMessaging()[0].getMessage().getText() != null)
				message = bean.getEntry()[0].getMessaging()[0].getMessage().getText();
		}
		return message;
	}

	public ReplyObj getReplyFromJson(String jsonPayload) {
		ReplyObj reply = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			reply = mapper.readValue(jsonPayload, ReplyObj.class);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return reply;
	}

	public String getJsonPayload(String jsonSource) {
		StringBuilder builder = new StringBuilder();
		ApplicationContext ctx = new FileSystemXmlApplicationContext();
		Resource resource = ctx.getResource(jsonPath + jsonSource);
		logger.info(" resource :: " + resource.exists() + " " + resource.getFilename());
		if (resource.exists()) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
				Stream<String> stream = reader.lines();
				Iterator<String> itr = stream.iterator();
				while (itr.hasNext()) {
					builder.append(itr.next());
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return builder.toString();
	}

	public String getMessagePayload(PageObjectBean bean) {
		String message = null;
		if (bean.getEntry()[0].getMessaging()[0].getMessage() != null) {
			if (bean.getEntry()[0].getMessaging()[0].getMessage().getQuick_reply() != null
					&& bean.getEntry()[0].getMessaging()[0].getMessage().getQuick_reply().getPayload() != null) {
				message = bean.getEntry()[0].getMessaging()[0].getMessage().getQuick_reply().getPayload();
			} else if (bean.getEntry()[0].getMessaging()[0].getMessage().getText() != null) {
				message = bean.getEntry()[0].getMessaging()[0].getMessage().getText();
			}
		} else if (bean.getEntry()[0].getMessaging()[0].getPostback() != null) {
			message = bean.getEntry()[0].getMessaging()[0].getPostback().getPayload();
		}
		return message;
	}

	public <T extends Serializable> T postReply(URI url, Object request, Class<T> responseType)
			throws RestClientException {
		RestTemplate rest = new RestTemplate();
		return rest.postForObject(url, request, responseType);
	}

	public URI getURI() throws URISyntaxException {
		URI uri = new URI(messenger_url + access_token);
		return uri;
	}

}
