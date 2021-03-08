package com.longpc.service;

import com.longpc.entity.UserEntity;

public interface IUserService extends IWebService {
	public UserEntity checkLogin(String email,String password) throws Exception;
	public UserEntity register(UserEntity userEntity) throws Exception;
}
