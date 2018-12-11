package com.digitail.bean;

import java.io.Serializable;

public class PageObjectBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String object;
	private Entry[] entry;

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public Entry[] getEntry() {
		return entry;
	}

	public void setEntry(Entry[] entry) {
		this.entry = entry;
	}

}
