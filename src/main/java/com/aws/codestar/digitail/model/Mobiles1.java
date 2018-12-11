package com.digitail.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digitail.util.DigitailStringConstants;

@Component
public class Mobiles1 extends ProductModel {

	@Value("${Mobiles1Title}")
	private String title;
	@Value("${Mobiles1SubTitle}")
	private String subtitle;
	@Value("${Mobiles1ItemURL}")
	private String item_url;
	@Value("${Mobiles1ImageURL}")
	private String image_url;
	@Value("${Mobiles1Next}")
	private String nextPayload;

	@PostConstruct
	public void initialize() {
		getAttributes().put(DigitailStringConstants.TITLE, title);
		getAttributes().put(DigitailStringConstants.SUBTITLE, subtitle);
		getAttributes().put(DigitailStringConstants.ITEM_URL, item_url);
		getAttributes().put(DigitailStringConstants.IMAGE_URL, image_url);
		getAttributes().put(DigitailStringConstants.NEXT_PAYLOAD, nextPayload);
	}
}
