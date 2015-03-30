package com.ingesup.truck.service;

import com.ingesup.truck.exception.DriverNotFoundException;
import com.ingesup.truck_center.model.Alert;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
public interface AlertService extends BaseService<Alert, Integer> {

	Alert getFirstByDriverId(final Integer driverId) throws DriverNotFoundException;
}
