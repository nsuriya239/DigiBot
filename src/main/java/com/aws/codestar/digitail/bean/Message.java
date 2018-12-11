package com.aws.codestar.digitail.bean;

import java.io.Serializable;

import com.aws.codestar.digitail.bean.reply.QuickReply;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private String mid;
	@JsonInclude(Include.NON_NULL)
	private int seq;
	@JsonInclude(Include.NON_NULL)
	private String text;
	@JsonInclude(Include.NON_NULL)
	private Attachment[] attachments;
	@JsonInclude(Include.NON_NULL)
	private QuickReply quick_reply;

	public QuickReply getQuick_reply() {
		return quick_reply;
	}

	public void setQuick_reply(QuickReply quick_reply) {
		this.quick_reply = quick_reply;
	}

	public String getMid() {
		return mid;
	}

	public Attachment[] getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachment[] attachments) {
		this.attachments = attachments;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
