package com.userdemo.controller;

public class LoginResponse {
	Status responsestatus;
	public String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Status getResponsestatus() {
		return responsestatus;
	}

	public void setResponsestatus(Status responsestatus) {
		this.responsestatus = responsestatus;
	}

}