package com.aws.codestar.digitail.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class UserProfile implements Serializable {

	@JsonInclude(Include.NON_NULL)
	private String first_name;
	@JsonInclude(Include.NON_NULL)
	private String last_name;
	@JsonInclude(Include.NON_NULL)
	private String profile_pic;
	@JsonInclude(Include.NON_NULL)
	private String locale;
	@JsonInclude(Include.NON_NULL)
	private String gender;
	@JsonInclude(Include.NON_NULL)
	private String timezone;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

}
