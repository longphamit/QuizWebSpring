package com.longpc.repository;

import com.longpc.entity.UserEntity;

public interface IUserRepository extends IJPARepository<UserEntity> {
	public UserEntity findById(String email) throws Exception;
	public UserEntity createUser(UserEntity userEntity) throws Exception;
}
