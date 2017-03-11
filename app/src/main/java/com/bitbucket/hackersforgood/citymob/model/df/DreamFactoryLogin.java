package com.bitbucket.hackersforgood.citymob.model.df;

public class DreamFactoryLogin {

	private String email;
	private String password;
	private Integer duration;

	public DreamFactoryLogin() {
	}

	public DreamFactoryLogin(String email, String password, Integer duration) {
		this.email = email;
		this.password = password;
		this.duration = duration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
}
