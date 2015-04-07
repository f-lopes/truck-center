package com.ingesup.truckcenter.service;

import com.ingesup.truckcenter.exception.DriverNotFoundException;
import com.ingesup.truckcenter.model.Alert;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
public interface AlertService extends BaseService<Alert, String> {

	Alert getFirstByDriverId(final String driverId) throws DriverNotFoundException;
}
