package com.ntscorp.intern.reservation.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.reservation.controller.request.LoginRequest;

@RestController
@RequestMapping("/api")
public class AuthController {
	private static final boolean VALID = false;
	private static final boolean INVALID = true;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody
	LoginRequest loginRequest, HttpSession session) {
		String email = loginRequest.getEmail();

		if (isNotVaildatedEmail(email)) {
			throw new IllegalArgumentException("arguments = [email: " + email + "]");
		}

		session.setAttribute("email", email);

		return ResponseEntity.ok("success");
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();

		return ResponseEntity.ok("success");
	}

	private boolean isNotVaildatedEmail(String email) {
		String regexValidation = "^[0-9a-zA-Z]{4,20}@[0-9a-zA-Z]{1,100}[.][0-9a-zA-Z]{2,10}$";
		if (Pattern.matches(regexValidation, email)) {
			return VALID;
		}
		return INVALID;
	}
}
