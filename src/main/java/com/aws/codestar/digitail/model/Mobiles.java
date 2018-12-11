package com.digitail.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digitail.util.DigitailStringConstants;

@Component
public class Mobiles extends ProductModel {

	@Value("${MobilesTitle}")
	private String title;
	@Value("${MobilesSubTitle}")
	private String subtitle;
	@Value("${MobilesItemURL}")
	private String item_url;
	@Value("${MobilesImageURL}")
	private String image_url;
	@Value("${MobilesNext}")
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
