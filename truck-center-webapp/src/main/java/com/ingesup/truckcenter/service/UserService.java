package com.ingesup.truckcenter.service;

import com.ingesup.truckcenter.model.User;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
public interface UserService extends BaseService<User, String> {

	User getByEmail(String email);

	User authenticate(String email, String password);
}
