package com.ingesup.truckcenter.database;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
@ConfigurationProperties(prefix = TruckCenterProperties.PREFIX)
public class TruckCenterProperties {

	public static final String PREFIX = "truck-center";
	public static final char SEPARATOR = ',';

	private boolean initialize = false;

	private String roles;

	private String adminEmail;

	private String adminPassword;

	private String adminFirstname;

	private String adminLastname;

	public boolean isInitialize() {
		return initialize;
	}

	public void setInitialize(boolean initialize) {
		this.initialize = initialize;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminFirstname() {
		return adminFirstname;
	}

	public void setAdminFirstname(String adminFirstname) {
		this.adminFirstname = adminFirstname;
	}

	public String getAdminLastname() {
		return adminLastname;
	}

	public void setAdminLastname(String adminLastname) {
		this.adminLastname = adminLastname;
	}
}
