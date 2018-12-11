package com.aws.codestar.digitail.bean.reply;

import java.io.Serializable;

import com.aws.codestar.digitail.bean.Attachment;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ReplyMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private String text;
	@JsonInclude(Include.NON_NULL)
	private Attachment attachment;
	@JsonInclude(Include.NON_NULL)
	private QuickReply[] quick_replies;

	public String getText() {
		return text;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void setText(String text) {
		this.text = text;
	}

	public QuickReply[] getQuick_replies() {
		return quick_replies;
	}

	public void setQuick_replies(QuickReply[] quick_replies) {
		this.quick_replies = quick_replies;
	}

}
