package com.springboot.loginapp.model;

/**
 * <P>
 * This Class is used to pass user value to the 
 * </P>
 * @author munirathinavel
 *
 */
public class User {

	long id;

	String name;

	String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", password=" + password;
	}

}
