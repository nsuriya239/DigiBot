package com.aws.codestar.digitail.bean.reply;

import java.io.Serializable;

public class ReplyObj implements Serializable {

	private static final long serialVersionUID = 1L;
	private Recipient recipient;
	private ReplyMessage message;	

	public ReplyMessage getMessage() {
		return message;
	}

	public void setMessage(ReplyMessage message) {
		this.message = message;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	@Override
	public String toString() {
		return "ClassPojo [message = " + message + ", recipient = " + recipient + "]";
	}

}
