package com.longpc.service.impl;

import org.mindrot.jbcrypt.BCrypt;
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
		UserEntity userEntity=userRepository.findById(email);
		if(userEntity!=null) {
			if(BCrypt.checkpw(password, userEntity.getPassword())) {
				return userEntity;
			}
		}
		return null;
	}

	public UserEntity register(UserEntity userEntity)throws Exception {
		return userRepository.createUser(userEntity);
	}
}
