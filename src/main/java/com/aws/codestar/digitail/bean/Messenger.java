package com.digitail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Messenger implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private String timestamp;
	@JsonInclude(Include.NON_NULL)
	private Sender sender;
	@JsonInclude(Include.NON_NULL)
	private Recipient recipient;
	@JsonInclude(Include.NON_NULL)
	private Message message;
	@JsonInclude(Include.NON_NULL)
	private Postback postback;
	@JsonInclude(Include.NON_NULL)
	private AccountLink account_linking;

	public Postback getPostback() {
		return postback;
	}

	public void setPostback(Postback postback) {
		this.postback = postback;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Recipient getRecipient() {
		return recipient;
	}

	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public AccountLink getAccount_linking() {
		return account_linking;
	}

	public void setAccount_linking(AccountLink account_linking) {
		this.account_linking = account_linking;
	}

}
