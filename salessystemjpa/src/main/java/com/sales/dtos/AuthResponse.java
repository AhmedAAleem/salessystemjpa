package com.sales.dtos;

public class AuthResponse {
	
	private String email;
	private String accessToken;

	public AuthResponse() {
	}

	public AuthResponse(String email, String accessToken) {
		this.email = email;
		this.accessToken = accessToken;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public String toString() {
		return "AuthResponse{" + "email='" + email + '\'' + ", accessToken='" + accessToken + '\'' + '}';
	}
}