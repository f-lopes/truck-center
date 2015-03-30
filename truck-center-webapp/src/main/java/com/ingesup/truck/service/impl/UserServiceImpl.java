package com.ingesup.truck.service.impl;

import com.ingesup.truck.repository.UserRepository;
import com.ingesup.truck_center.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.mapping.Attributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class UserServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	private final Attributes2GrantedAuthoritiesMapper grantedAuthoritiesMapper = new SimpleAttributes2GrantedAuthoritiesMapper();

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
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
}
