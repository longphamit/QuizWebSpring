package com.longpc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.longpc.entity.UserEntity;
import com.longpc.util.FieldConstant;

@Controller
public class LogoutController {
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		String url="error";
		try {
			session.removeAttribute(FieldConstant.USER);
			session.invalidate();
			url="redirect:/";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}
}
