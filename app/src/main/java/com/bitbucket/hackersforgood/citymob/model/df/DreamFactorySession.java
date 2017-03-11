package com.bitbucket.hackersforgood.citymob.model.df;

public class DreamFactorySession {

	private String id;
	private String email;
	private String first_name;
	private String last_name;
	private String display_name;
	private Boolean is_sys_admin;
	private String role;
	private String last_login_date;
	private DreamFactorySessionApp[] app_groups;
	private DreamFactorySessionApp[] no_group_apps;
	private String session_id;
	private String ticket;
	private String ticket_expiry;

	public DreamFactorySession() {
	}

	public DreamFactorySession(String id, String email, String first_name, String last_name, String display_name, Boolean is_sys_admin, String role, String last_login_date, DreamFactorySessionApp[] app_groups, DreamFactorySessionApp[] no_group_apps, String session_id, String ticket, String ticket_expiry) {
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.display_name = display_name;
		this.is_sys_admin = is_sys_admin;
		this.role = role;
		this.last_login_date = last_login_date;
		this.app_groups = app_groups;
		this.no_group_apps = no_group_apps;
		this.session_id = session_id;
		this.ticket = ticket;
		this.ticket_expiry = ticket_expiry;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public Boolean getIs_sys_admin() {
		return is_sys_admin;
	}

	public void setIs_sys_admin(Boolean is_sys_admin) {
		this.is_sys_admin = is_sys_admin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	public DreamFactorySessionApp[] getApp_groups() {
		return app_groups;
	}

	public void setApp_groups(DreamFactorySessionApp[] app_groups) {
		this.app_groups = app_groups;
	}

	public DreamFactorySessionApp[] getNo_group_apps() {
		return no_group_apps;
	}

	public void setNo_group_apps(DreamFactorySessionApp[] no_group_apps) {
		this.no_group_apps = no_group_apps;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getTicket_expiry() {
		return ticket_expiry;
	}

	public void setTicket_expiry(String ticket_expiry) {
		this.ticket_expiry = ticket_expiry;
	}
}
