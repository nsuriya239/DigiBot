package com.aws.codestar.digitail;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aws.codestar.digitail.bean.PageObjectBean;
import com.aws.codestar.digitail.bean.reply.ReplyObj;
import com.aws.codestar.digitail.command.PayloadCommand;
import com.aws.codestar.digitail.model.Laptops;
import com.aws.codestar.digitail.model.Laptops1;
import com.aws.codestar.digitail.model.Mobiles;
import com.aws.codestar.digitail.model.Mobiles1;
import com.aws.codestar.digitail.model.ProductModel;
import com.aws.codestar.digitail.util.BotUtil;
import com.aws.codestar.digitail.util.DigitailStringConstants;

@Component
public class QuickReplyPayloadCommand implements PayloadCommand {

	Logger logger = LoggerFactory.getLogger(QuickReplyPayloadCommand.class);
	@Autowired
	BotUtil util;
	@Value("#{'${quickReplyPayloads}'.split(',')}")
	private List<String> quickReplyPayloadsList;
	@Autowired
	private Laptops laptop;
	@Autowired
	private Laptops1 laptop1;
	@Autowired
	private Mobiles mobiles;
	@Autowired
	private Mobiles1 mobiles1;
	Map<String, ProductModel> modelMap = new HashMap<String, ProductModel>();

	@PostConstruct
	public void initialize() {
		modelMap.put(DigitailStringConstants.LAPTOPS, laptop);
		modelMap.put(DigitailStringConstants.LAPTOPS1, laptop1);
		modelMap.put(DigitailStringConstants.MOBILES, mobiles);
		modelMap.put(DigitailStringConstants.MOBILES1, mobiles1);
	}

	@Override
	public Serializable execute(PageObjectBean bean) {
		ReplyObj reply = util.getReplyObject(DigitailStringConstants.IS_GENERIC_ELEMENT, 1);
		reply.getRecipient().setId(util.getSenderId(bean));
		reply.getMessage().getAttachment().setType(DigitailStringConstants.TEMPLATE);
		reply.getMessage().getAttachment().getPayload().setTemplate_type(DigitailStringConstants.GENERIC_TEMPLATE_TYPE);

		String payload = "";
		if (bean.getEntry()[0].getMessaging()[0].getMessage() != null
				&& bean.getEntry()[0].getMessaging()[0].getMessage().getText() != null) {
			payload = bean.getEntry()[0].getMessaging()[0].getMessage().getText();
		} else if (bean.getEntry()[0].getMessaging()[0].getPostback() != null
				&& bean.getEntry()[0].getMessaging()[0].getPostback().getPayload() != null) {
			payload = bean.getEntry()[0].getMessaging()[0].getPostback().getPayload();
		}

		logger.info("Quick Reply payload command :: " + payload);
		constructResponse(modelMap.get(payload), reply);

		return reply;
	}

	private void constructResponse(ProductModel productModel, ReplyObj reply) {
		reply.getMessage().getAttachment().getPayload().getElements()[0]
				.setTitle(productModel.getAttributes().get(DigitailStringConstants.TITLE));
		reply.getMessage().getAttachment().getPayload().getElements()[0]
				.setSubtitle(productModel.getAttributes().get(DigitailStringConstants.SUBTITLE));
		reply.getMessage().getAttachment().getPayload().getElements()[0]
				.setImage_url(productModel.getAttributes().get(DigitailStringConstants.IMAGE_URL));
		reply.getMessage().getAttachment().getPayload().getElements()[0]
				.setItem_url(productModel.getAttributes().get(DigitailStringConstants.ITEM_URL));

		reply.getMessage().getAttachment().getPayload().getElements()[0].getButtons()[0]
				.setType(DigitailStringConstants.WEB_URL);
		reply.getMessage().getAttachment().getPayload().getElements()[0].getButtons()[0].setTitle("View in Website");
		reply.getMessage().getAttachment().getPayload().getElements()[0].getButtons()[0]
				.setUrl(productModel.getAttributes().get(DigitailStringConstants.ITEM_URL));

		reply.getMessage().getAttachment().getPayload().getElements()[0].getButtons()[1]
				.setType(DigitailStringConstants.POSTBACK);
		reply.getMessage().getAttachment().getPayload().getElements()[0].getButtons()[1].setTitle("Next");
		reply.getMessage().getAttachment().getPayload().getElements()[0].getButtons()[1]
				.setPayload(productModel.getAttributes().get(DigitailStringConstants.NEXT_PAYLOAD));
	}

}
