package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.model.Driver;
import com.ingesup.truckcenter.model.Role;
import com.ingesup.truckcenter.repository.BaseRepository;
import com.ingesup.truckcenter.repository.DriverRepository;
import com.ingesup.truckcenter.repository.RoleRepository;
import com.ingesup.truckcenter.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class DriverServiceImpl extends BaseServiceImpl<Driver, Integer> implements DriverService {

	private final DriverRepository driverRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	@Autowired
	public DriverServiceImpl(DriverRepository driverRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
		this.driverRepository = driverRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public BaseRepository<Driver, Integer> getRepository() {
		return this.driverRepository;
	}

	@Override
	public Driver add(Driver driver) {
		driver.setPassword(passwordEncoder.encode(driver.getPassword()));
		driver.setRoles(getDbRoles(driver.getRoles()));

		return super.add(driver);
	}

	private Set<Role> getDbRoles(Set<Role> roles) {
		Set<Role> dbRoles = new HashSet<>();

		for (Role role : roles) {
			dbRoles.add(roleRepository.findByName(role.getName()));
		}

		return dbRoles;
	}
}
