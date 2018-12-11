package com.aws.codestar.digitail.model;

public class UserLogin {

	private String userName;
	private String pwd;
	private String redirect_uri;
	private String account_linking_token;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getAccount_linking_token() {
		return account_linking_token;
	}

	public void setAccount_linking_token(String account_linking_token) {
		this.account_linking_token = account_linking_token;
	}

}
