package com.ingesup.truckcenter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lopes_f on 4/10/2015.
 * <florian.lopes@outlook.com>
 */
@ConfigurationProperties(prefix = SecurityProperties.PREFIX)
public class SecurityProperties {

	public static final String PREFIX = "truck-center.security";

	private Basic basic = new Basic();

	public static class Basic {

		private String username;

		private String password;

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
	}

	public Basic getBasic() {
		return basic;
	}

	public void setBasic(Basic basic) {
		this.basic = basic;
	}
}
