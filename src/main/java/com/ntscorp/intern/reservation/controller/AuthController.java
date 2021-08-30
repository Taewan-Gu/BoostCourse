package com.ntscorp.intern.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntscorp.intern.common.utils.ValidationUtils;
import com.ntscorp.intern.reservation.model.Login;

@RestController
@RequestMapping("/api")
public class AuthController {
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody
	Login loginRequest, HttpSession session) {
		String email = loginRequest.getEmail();

		if (ValidationUtils.isNotVaildatedEmail(email)) {
			throw new IllegalArgumentException("arguments = [email: " + email + "]");
		}

		session.setAttribute("email", email);

		return ResponseEntity.ok("success");
	}

	@PostMapping("/logincheck")
	public ResponseEntity<Login> loginCheck(HttpSession session) {
		Login loginCheck = new Login();

		if (session.getAttribute("email") == null) {
			return new ResponseEntity<>(loginCheck, HttpStatus.NO_CONTENT);
		}

		loginCheck.setEmail(session.getAttribute("email").toString());

		return ResponseEntity.ok(loginCheck);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();

		return ResponseEntity.ok("success");
	}
}