package com.ingesup.truck.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class SecurityUtil {

	public static TruckCenterUserDetails getCurrentLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication == null ? null : (TruckCenterUserDetails) authentication.getPrincipal();
	}

	public static void loginUser(TruckCenterUserDetails user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
