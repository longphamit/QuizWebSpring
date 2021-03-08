package com.longpc.controller;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.longpc.dto.MessageDTO;
import com.longpc.entity.UserEntity;
import com.longpc.service.IUserService;
import com.longpc.util.FieldConstant;
import com.longpc.util.RoleConstant;

@Controller
public class LoginController {
	@Autowired
	IUserService userService;

	@GetMapping("/")
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("txtEmail") String email, @RequestParam("txtPassword") String password,
			ModelMap model, HttpSession session) {
		String url = "error";
		MessageDTO messageDTO = new MessageDTO();
		try {
			UserEntity userEntity = userService.checkLogin(email, password);
			model.addAttribute("user", userEntity);
			messageDTO.setContent("welcome " + userEntity.getEmail());
			messageDTO.setStatus(true);
			session.setAttribute("message", messageDTO);
			if (userEntity.getRole().equals(RoleConstant.AMIN_ROLE)) {
				url = "redirect:/admin";
			} else {
				url = "redirect:/home";
			}
			session.setAttribute(FieldConstant.USER, userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof NoResultException) {
				messageDTO.setContent("Bạn nhập sai email hoặc mật khẩu");
				messageDTO.setStatus(false);
				session.setAttribute("message", messageDTO);
				url = "redirect:/";
			}
		}
		return url;
	}

}
