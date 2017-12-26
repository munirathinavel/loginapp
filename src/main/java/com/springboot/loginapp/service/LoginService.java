package com.springboot.loginapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.loginapp.dao.ILoginDao;
import com.springboot.loginapp.model.User;

@Service
public class LoginService implements ILoginService {

	@Autowired
	ILoginDao loginDao;

	/* (non-Javadoc)
	 * @see com.springboot.loginapp.service.ILoginService#validateUser(com.springboot.loginapp.model.User)
	 */
	@Override
	public boolean validateUser(User user) {
		return loginDao.validateUser(user);
	}
}