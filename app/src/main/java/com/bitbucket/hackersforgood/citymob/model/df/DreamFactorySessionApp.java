package com.bitbucket.hackersforgood.citymob.model.df;


public class DreamFactorySessionApp {

	private Integer id;
	private String name;
	private String description;
	private Boolean is_url_external;
	private String launch_url;
	private Boolean requires_fullscreen;
	private Boolean allow_fullscreen_toggle;
	private String toggle_location;
	private Boolean is_default;

	public DreamFactorySessionApp() {
	}

	public DreamFactorySessionApp(Integer id, String name, String description, Boolean is_url_external, String launch_url, Boolean requires_fullscreen, Boolean allow_fullscreen_toggle, String toggle_location, Boolean is_default) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.is_url_external = is_url_external;
		this.launch_url = launch_url;
		this.requires_fullscreen = requires_fullscreen;
		this.allow_fullscreen_toggle = allow_fullscreen_toggle;
		this.toggle_location = toggle_location;
		this.is_default = is_default;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIs_url_external() {
		return is_url_external;
	}

	public void setIs_url_external(Boolean is_url_external) {
		this.is_url_external = is_url_external;
	}

	public String getLaunch_url() {
		return launch_url;
	}

	public void setLaunch_url(String launch_url) {
		this.launch_url = launch_url;
	}

	public Boolean getRequires_fullscreen() {
		return requires_fullscreen;
	}

	public void setRequires_fullscreen(Boolean requires_fullscreen) {
		this.requires_fullscreen = requires_fullscreen;
	}

	public Boolean getAllow_fullscreen_toggle() {
		return allow_fullscreen_toggle;
	}

	public void setAllow_fullscreen_toggle(Boolean allow_fullscreen_toggle) {
		this.allow_fullscreen_toggle = allow_fullscreen_toggle;
	}

	public String getToggle_location() {
		return toggle_location;
	}

	public void setToggle_location(String toggle_location) {
		this.toggle_location = toggle_location;
	}

	public Boolean getIs_default() {
		return is_default;
	}

	public void setIs_default(Boolean is_default) {
		this.is_default = is_default;
	}
}
