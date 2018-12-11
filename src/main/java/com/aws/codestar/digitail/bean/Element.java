package com.aws.codestar.digitail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Element implements Serializable {

	@JsonInclude(Include.NON_NULL)
	private String title;
	@JsonInclude(Include.NON_NULL)
	private String item_url;
	@JsonInclude(Include.NON_NULL)
	private String image_url;
	@JsonInclude(Include.NON_NULL)
	private String subtitle;
	@JsonInclude(Include.NON_NULL)
	private Button[] buttons;
	@JsonInclude(Include.NON_NULL)
	private DefaultAction default_action;
	@JsonInclude(Include.NON_NULL)
	private String quantity;
	@JsonInclude(Include.NON_NULL)
	private String price;
	@JsonInclude(Include.NON_NULL)
	private String currency;

	public DefaultAction getDefault_action() {
		return default_action;
	}

	public void setDefault_action(DefaultAction default_action) {
		this.default_action = default_action;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getItem_url() {
		return item_url;
	}

	public void setItem_url(String item_url) {
		this.item_url = item_url;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Button[] getButtons() {
		return buttons;
	}

	public void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
