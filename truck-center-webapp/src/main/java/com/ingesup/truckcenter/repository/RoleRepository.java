package com.ingesup.truckcenter.repository;

import com.ingesup.truckcenter.model.Role;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
public interface RoleRepository extends BaseRepository<Role, Integer> {

	Role findByName(String name);
}
