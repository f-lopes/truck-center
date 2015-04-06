package com.ingesup.truckcenter.repository;

import com.ingesup.truckcenter.model.User;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
public interface UserRepository extends BaseRepository<User, Integer> {

	User findFirstByEmail(String email);
}
