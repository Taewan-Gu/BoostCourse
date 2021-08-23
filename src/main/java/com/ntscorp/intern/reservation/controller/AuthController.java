package com.ntscorp.intern.reservation.controller;

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

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody
	LoginRequest loginRequest, HttpSession session) {
		session.setAttribute("email", loginRequest.getEmail());

		return ResponseEntity.ok("success");
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {
		session.invalidate();

		return ResponseEntity.ok("success");
	}
}
