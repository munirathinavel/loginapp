package com.springboot.loginapp.dao;

import com.springboot.loginapp.model.User;

public interface ILoginDao {

	/*
	 * This method is used to validate & authenticate the user.
	 */
	boolean validateUser(User user);

}