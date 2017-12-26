package com.springboot.loginapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springboot.loginapp.model.User;

/**
 * <P>
 * This class is responsible for interacting with the database.
 * </P>
 * 
 * @author munirathinavel
 *
 */
@Repository
public class LoginDao implements ILoginDao {

	private static final Logger logger = Logger.getLogger(LoginDao.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	/*
	 * This method is used to validate & authenticate the user.
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.springboot.loginapp.dao.ILoginDao#validateUser(com.springboot.loginapp.
	 * model.User)
	 */
	@Override
	public boolean validateUser(User user) {
		if (user == null) {
			throw new RuntimeException("User is empty!");
		}
		logger.debug("Inside validateUser Method with User:" + user);

		String sql = "SELECT * FROM USER_LOGIN WHERE name = ? and password = ? ";

		List<User> users = (List<User>) jdbcTemplate.query(sql, new Object[] { user.getName(), user.getPassword() },
				new CustomerRowMapper());
		logger.debug("users (size)==>" + users.size());
		if (users == null || users.isEmpty() || users.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * Customer Row Mapper
	 */
	class CustomerRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			return user;
		}

	}
}