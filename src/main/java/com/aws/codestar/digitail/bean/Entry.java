package com.digitail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Entry implements Serializable {

	@JsonInclude(Include.NON_NULL)
	private String id;
	@JsonInclude(Include.NON_NULL)
	private String time;
	@JsonInclude(Include.NON_NULL)
	private Messenger[] messaging;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Messenger[] getMessaging() {
		return messaging;
	}

	public void setMessaging(Messenger[] messaging) {
		this.messaging = messaging;
	}

}
