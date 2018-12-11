package com.digitail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private String type;
	@JsonInclude(Include.NON_NULL)
	private Payload payload;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

}
