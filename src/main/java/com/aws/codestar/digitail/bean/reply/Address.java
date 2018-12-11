package com.digitail.bean.reply;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Address implements Serializable {

	@JsonInclude(Include.NON_NULL)
	private String street_1;
	@JsonInclude(Include.NON_NULL)
	private String street_2;
	@JsonInclude(Include.NON_NULL)
	private String city;
	@JsonInclude(Include.NON_NULL)
	private String postal_code;
	@JsonInclude(Include.NON_NULL)
	private String state;
	@JsonInclude(Include.NON_NULL)
	private String country;

	public String getStreet_1() {
		return street_1;
	}

	public void setStreet_1(String street_1) {
		this.street_1 = street_1;
	}

	public String getStreet_2() {
		return street_2;
	}

	public void setStreet_2(String street_2) {
		this.street_2 = street_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
