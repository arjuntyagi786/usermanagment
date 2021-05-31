package com.userdemo.repository;

public class LoginResult {
	Status responsestatus;
public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
public String token;
public Status getResponsestatus() {
	return responsestatus;
}
public void setResponsestatus(Status responsestatus) {
	this.responsestatus = responsestatus;
}


}