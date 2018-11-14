package com.licenta.entity;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3483775832228586172L;
	private String userName = "email_address";
	private String password = "password_temp";
	private String age = "0/0/0000";
	private String name = "name_temp";
	private String role = "role";
	private String history = "history";
	public User(String userName, String password, String age, String name, String role, String history) {
		super();
		this.userName = userName;
		this.password = password;
		this.age = age;
		this.name = name;
		this.role = role;
		this.history = history;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", age=" + age + ", name=" + name + ", role="
				+ role + ", history=" + history + "]";
	}
}
