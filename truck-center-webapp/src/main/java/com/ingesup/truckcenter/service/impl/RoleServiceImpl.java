package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.model.Role;
import com.ingesup.truckcenter.repository.BaseRepository;
import com.ingesup.truckcenter.repository.RoleRepository;
import com.ingesup.truckcenter.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 4/3/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Integer> implements RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public BaseRepository<Role, Integer> getRepository() {
		return this.roleRepository;
	}
}
