package com.springboot.loginapp.service;

import com.springboot.loginapp.model.User;

public interface ILoginService {

	boolean validateUser(User user);

}