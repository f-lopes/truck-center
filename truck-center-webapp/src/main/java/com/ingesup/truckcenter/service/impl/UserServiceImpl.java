package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.model.Role;
import com.ingesup.truckcenter.model.User;
import com.ingesup.truckcenter.repository.BaseRepository;
import com.ingesup.truckcenter.repository.RoleRepository;
import com.ingesup.truckcenter.repository.UserRepository;
import com.ingesup.truckcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.mapping.Attributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	private final Attributes2GrantedAuthoritiesMapper grantedAuthoritiesMapper = new SimpleAttributes2GrantedAuthoritiesMapper();

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User add(User entity) {
		entity.setPassword(this.passwordEncoder.encode(entity.getPassword()));
		entity.setRoles(getDbRoles(entity.getRoles()));

		return super.update(entity);
	}

	@Override
	public User getByEmail(String email) {
		return this.userRepository.findFirstByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User truckCenterUser = userRepository.findFirstByEmail(username);

		if (truckCenterUser == null) {
			throw new UsernameNotFoundException(username);
		}

		final Set<String> userRoles = truckCenterUser.getRolesAsString();
		return new org.springframework.security.core.userdetails.User(
				truckCenterUser.getEmail(), truckCenterUser.getPassword(),
				this.grantedAuthoritiesMapper.getGrantedAuthorities(userRoles));
	}

	private Set<Role> getDbRoles(Set<Role> roles) {
		Set<Role> dbRoles = new HashSet<>();

		for (Role role : roles) {
			dbRoles.add(this.roleRepository.findByName(role.getName()));
		}

		return dbRoles;
	}

	@Override
	public BaseRepository<User, String> getRepository() {
		return this.userRepository;
	}
}
