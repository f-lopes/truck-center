package com.ingesup.truckcenter.database;

import com.ingesup.truckcenter.model.Role;
import com.ingesup.truckcenter.model.RoleEnum;
import com.ingesup.truckcenter.model.User;
import com.ingesup.truckcenter.service.RoleService;
import com.ingesup.truckcenter.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
@Component
@EnableConfigurationProperties (value = {TruckCenterProperties.class})
public class DatabaseLoader {

	private final UserService userService;
	private final RoleService roleService;
	private final TruckCenterProperties truckCenterProperties;

	@Autowired
	public DatabaseLoader(UserService userService, RoleService roleService, TruckCenterProperties truckCenterProperties) {
		this.userService = userService;
		this.truckCenterProperties = truckCenterProperties;
		this.roleService = roleService;
	}

	@PostConstruct
	private void initDatabaseData() {
		if (truckCenterProperties.isInitialize()) {
			initRoles();
			initAdmin();
		}
	}

	private void initRoles() {
		Set<Role> roles = new HashSet<>();
		String[] rolesNames = StringUtils.split(truckCenterProperties.getRoles(), TruckCenterProperties.SEPARATOR);

		for (String roleName : rolesNames) {
			roles.add(new Role(roleName));
		}

		roleService.add(roles);
	}

	private void initAdmin() {
		User adminUser = new User(truckCenterProperties.getAdminFirstname(),
								  truckCenterProperties.getAdminLastname(),
								  truckCenterProperties.getAdminEmail(),
								  truckCenterProperties.getAdminPassword());
		adminUser.addRole(RoleEnum.ROLE_ADMIN);

		userService.add(adminUser);
	}
}
