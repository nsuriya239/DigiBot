package com.digitail.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digitail.util.DigitailStringConstants;

@Component
public class Laptops1 extends ProductModel {

	@Value("${Laptops1Title}")
	private String title;
	@Value("${Laptops1SubTitle}")
	private String subtitle;
	@Value("${Laptops1ItemURL}")
	private String item_url;
	@Value("${Laptops1ImageURL}")
	private String image_url;
	@Value("${Laptops1Next}")
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
