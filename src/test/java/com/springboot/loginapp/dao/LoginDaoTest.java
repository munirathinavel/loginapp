package com.springboot.loginapp.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.loginapp.config.LoginappApplication;
import com.springboot.loginapp.model.User;

import junit.framework.Assert;

/**
 * <p>
 * This class is used for testing the LoginDao class with various scenarios
 * </p>
 * 
 * @author munirathinavel
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LoginappApplication.class })
@ComponentScan(basePackages = "com.springboot.loginapp")
@Transactional
public class LoginDaoTest {

	@Autowired
	LoginDao loginDao;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		loginDao.jdbcTemplate = jdbcTemplate;
	}

	@Test
	public void testValidateUserForValidUser() {
		User user = new User();
		user.setName("vel");
		user.setPassword("123456");
		boolean result = loginDao.validateUser(user);

		Assert.assertSame(Boolean.TRUE, result);
	}

	@Test
	public void testValidateUserForInValidUser() {
		User user = new User();
		user.setName("ram");
		user.setPassword("23432");
		boolean result = loginDao.validateUser(user);

		Assert.assertSame(Boolean.FALSE, result);
	}

	@Test(expected = RuntimeException.class)
	public void testValidateUserForNull() {
		boolean result = loginDao.validateUser(null);

		Assert.assertSame(Boolean.FALSE, result);
	}

}
