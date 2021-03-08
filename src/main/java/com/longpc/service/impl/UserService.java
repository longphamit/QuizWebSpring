package com.longpc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longpc.entity.UserEntity;
import com.longpc.repository.IUserRepository;
import com.longpc.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	public IUserRepository userRepository;
	
	public UserEntity checkLogin(String email, String password) throws Exception {
		return userRepository.findById(email, password);
	}

	public UserEntity register(UserEntity userEntity)throws Exception {
		return userRepository.createUser(userEntity);
	}
}
