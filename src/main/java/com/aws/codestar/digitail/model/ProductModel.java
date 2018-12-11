package com.digitail.model;

import java.util.HashMap;
import java.util.Map;

public abstract class ProductModel {

	Map<String, String> attributes = new HashMap<String, String>();

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

}
