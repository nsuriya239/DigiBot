package com.aws.codestar.digitail.bean.reply;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Summary implements Serializable {

	@JsonInclude(Include.NON_NULL)
	private String subtotal;
	@JsonInclude(Include.NON_NULL)
	private String shipping_cost;
	@JsonInclude(Include.NON_NULL)
	private String total_tax;
	@JsonInclude(Include.NON_NULL)
	private String total_cost;

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(String shipping_cost) {
		this.shipping_cost = shipping_cost;
	}

	public String getTotal_tax() {
		return total_tax;
	}

	public void setTotal_tax(String total_tax) {
		this.total_tax = total_tax;
	}

	public String getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(String total_cost) {
		this.total_cost = total_cost;
	}

}
