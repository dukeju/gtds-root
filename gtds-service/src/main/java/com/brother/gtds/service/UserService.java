package com.brother.gtds.service;

import com.brother.gtds.model.User;

public interface UserService {

	//验证用户信息
	User validateUserInfo(String id, String password, Integer status);

}
