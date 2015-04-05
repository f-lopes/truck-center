package com.ingesup.truck.service;

import com.ingesup.truck_center.model.User;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
public interface UserService extends BaseService<User, Integer> {

	User getByEmail(String email);
}
