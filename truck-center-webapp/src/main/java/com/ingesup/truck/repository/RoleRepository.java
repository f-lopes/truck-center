package com.ingesup.truck.repository;

import com.ingesup.truck_center.model.Role;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
public interface RoleRepository extends BaseRepository<Role, Integer> {

	Role findByName(String name);
}
