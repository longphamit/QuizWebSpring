package com.longpc.controller;

import javax.servlet.http.HttpSession;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longpc.dto.MessageDTO;
import com.longpc.dto.RegisterDTO;
import com.longpc.entity.UserEntity;
import com.longpc.service.IUserService;
import com.longpc.util.FieldConstant;

@Controller
public class RegisterController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("/register")
	public String index() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(
			@RequestParam("txtName") String name,
			@RequestParam("txtPassword")String password,
			@RequestParam("txtEmail") String email,
			@RequestParam("txtPhone")String phone,
			HttpSession session) {
		String url="error";
		MessageDTO messageDTO = new MessageDTO();
		try {
			UserEntity userEntity= new UserEntity();
			userEntity.setEmail(email);
			userEntity.setPassword(password);
			userEntity.setName(name);
			userEntity.setPhone(phone);
			userEntity.setRole("CUSTOMER");
			userEntity.setStatus("1");
			userService.register(userEntity);
			messageDTO.setContent("Welcome "+userEntity.getEmail());
			messageDTO.setStatus(true);
			url="redirect:/home";
			session.setAttribute(FieldConstant.USER, userEntity);
			session.setAttribute(FieldConstant.MESSAGE, messageDTO);
		}catch (Exception e) {
			if(e.getMessage().contains("ConstraintViolationException")) {
				messageDTO.setContent("Email đã tồn tại");
				messageDTO.setStatus(false);
				session.setAttribute("message",messageDTO);
				url="register";
			}else {
				e.printStackTrace();
			}
		}
		return url;
	}
}
