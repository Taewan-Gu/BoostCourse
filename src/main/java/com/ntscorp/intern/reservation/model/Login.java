package com.ntscorp.intern.reservation.model;

public class Login {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "LoginRequest [email=" + email + "]";
	}
}