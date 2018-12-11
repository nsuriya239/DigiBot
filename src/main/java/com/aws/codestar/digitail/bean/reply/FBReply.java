package com.digitail.bean.reply;

import java.io.Serializable;

public class FBReply implements Serializable {

	private String message_id;
	private String recipient_id;

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public String getRecipient_id() {
		return recipient_id;
	}

	public void setRecipient_id(String recipient_id) {
		this.recipient_id = recipient_id;
	}

}
