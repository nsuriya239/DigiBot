package com.digitail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Button implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonInclude(Include.NON_NULL)
	private String type;
	@JsonInclude(Include.NON_NULL)
	private String url;
	@JsonInclude(Include.NON_NULL)
	private String title;
	@JsonInclude(Include.NON_NULL)
	private String payload;
	@JsonInclude(Include.NON_DEFAULT)
	private boolean messenger_extensions;
	@JsonInclude(Include.NON_NULL)
	private String webview_height_ratio;
	@JsonInclude(Include.NON_NULL)
	private String fallback_url;

	public boolean isMessenger_extensions() {
		return messenger_extensions;
	}

	public void setMessenger_extensions(boolean messenger_extensions) {
		this.messenger_extensions = messenger_extensions;
	}

	public String getWebview_height_ratio() {
		return webview_height_ratio;
	}

	public void setWebview_height_ratio(String webview_height_ratio) {
		this.webview_height_ratio = webview_height_ratio;
	}

	public String getFallback_url() {
		return fallback_url;
	}

	public void setFallback_url(String fallback_url) {
		this.fallback_url = fallback_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

}
