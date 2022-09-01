package com.example.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetControler {

	@GetMapping("/greet/{username}")
	public String greetMessage(HttpServletRequest request, @PathVariable("username") String username) {

		// get the attirbute from the interceptor
		String message = (String) request.getAttribute("message");
		System.out.println(message);

		// set the new data and send it back;
		request.setAttribute("newmessage", "work in controller");
		return "Great Day ...!" + username;
	}

//	@
//	public String errMsg() {
//		return "error occored..!";
//
//	}
}
