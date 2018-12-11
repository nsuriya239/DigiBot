package com.aws.codestar.digitail.model;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aws.codestar.digitail.util.DigitailStringConstants;

@Component
public class Laptops extends ProductModel {

	@Value("${LaptopsTitle}")
	private String title;
	@Value("${LaptopsSubTitle}")
	private String subtitle;
	@Value("${LaptopsItemURL}")
	private String item_url;
	@Value("${LaptopsImageURL}")
	private String image_url;
	@Value("${LaptopsNext}")
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
