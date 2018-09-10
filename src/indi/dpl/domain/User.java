package indi.dpl.domain;

/**
*
*Description:javaBean,用于存放数据查询返回的实体
*@author Karoy
*Start time:2018年9月7日
*Version:v0.1 2018年9月7日
*
*/

public class User {
	private String username;
	private String password;
	private String verifyCode;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifycode) {
		this.verifyCode = verifycode;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", verifycode=" + verifyCode + "]";
	}
}
