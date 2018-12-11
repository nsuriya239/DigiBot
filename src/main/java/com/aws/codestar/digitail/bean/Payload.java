package com.aws.codestar.digitail.bean;

import java.io.Serializable;

import com.aws.codestar.digitail.bean.reply.Address;
import com.aws.codestar.digitail.bean.reply.Adjustment;
import com.aws.codestar.digitail.bean.reply.Summary;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Payload implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private String text;
	@JsonInclude(Include.NON_NULL)
	private String template_type;
	@JsonInclude(Include.NON_NULL)
	private Button[] buttons;
	@JsonInclude(Include.NON_NULL)
	private Element[] elements;
	@JsonInclude(Include.NON_NULL)
	private String url;
	@JsonInclude(Include.NON_NULL)
	private String top_element_style;
	@JsonInclude(Include.NON_NULL)
	private String recipient_name;
	@JsonInclude(Include.NON_NULL)
	private String order_number;
	@JsonInclude(Include.NON_NULL)
	private String currency;
	@JsonInclude(Include.NON_NULL)
	private String payment_method;
	@JsonInclude(Include.NON_NULL)
	private String order_url;
	@JsonInclude(Include.NON_DEFAULT)
	private long timestamp;
	@JsonInclude(Include.NON_NULL)
	private Address address;
	@JsonInclude(Include.NON_NULL)
	private Summary summary;
	@JsonInclude(Include.NON_NULL)
	private Adjustment[] adjustments;

	public String getTop_element_style() {
		return top_element_style;
	}

	public void setTop_element_style(String top_element_style) {
		this.top_element_style = top_element_style;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Element[] getElements() {
		return elements;
	}

	public void setElements(Element[] elements) {
		this.elements = elements;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTemplate_type() {
		return template_type;
	}

	public void setTemplate_type(String template_type) {
		this.template_type = template_type;
	}

	public Button[] getButtons() {
		return buttons;
	}

	public void setButtons(Button[] buttons) {
		this.buttons = buttons;
	}

	public String getRecipient_name() {
		return recipient_name;
	}

	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getOrder_url() {
		return order_url;
	}

	public void setOrder_url(String order_url) {
		this.order_url = order_url;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public Adjustment[] getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(Adjustment[] adjustments) {
		this.adjustments = adjustments;
	}

}
